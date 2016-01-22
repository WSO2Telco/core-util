/*******************************************************************************
 * Copyright  (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 * 
 * WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.wso2telco.mnc.resolver.dnsssl;

import java.io.IOException;

import org.xbill.DNS.Header;
import org.xbill.DNS.Message;
import org.xbill.DNS.Options;
import org.xbill.DNS.WireParseException;

// TODO: Auto-generated Javadoc
/**
 * An implementation of Resolver that sends an ENUM query to PathFinder SSL
 * server.
 * 
 * @author Chandan Gupta
 * @version $Revision: 1.4 $
 */

public class SSLResolver {

	/** The ssl client. */
	private SSLClient sslClient;

	/**
	 * Creates a SimpleResolver that will query the specified host at specified
	 * port.
	 *
	 * @param sslClient            the underlying SSL client object encapsulating the SSL socket
	 */
	public SSLResolver(final SSLClient sslClient) {
		this.sslClient = sslClient;
	}

	/**
	 * Method parseMessage converts byte stream to message object.
	 *
	 * @param b            byte[] input byte array
	 * @return Message message object
	 * @throws WireParseException the wire parse exception
	 */
	private Message parseMessage(final byte[] b) throws WireParseException {
		try {
			return new Message(b);
		} catch (IOException e) {
			if (Options.check("verbose")) {
				e.printStackTrace();
			}
			if (!(e instanceof WireParseException)) {
				e = new WireParseException("Error parsing message");
			}
			throw (WireParseException) e;
		}
	}

	/**
	 * Sends a message to a single server and waits for a response. No checking
	 * is done to ensure that the response is associated with the query.
	 *
	 * @param query            The query to send.
	 * @return The response. * @throws IOException An error occurred while
	 *         sending or receiving.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Message send(final Message query) throws IOException {

		byte[] out = query.toWire(Message.MAXLENGTH);

		byte[] in = sslClient.sendrecv(out);

		// Check that the response length
		if (in.length < Header.LENGTH) {
			throw new WireParseException("invalid DNS header - " + "too short");
		}

		/*
		 * Check that the response ID matches the query ID. We want to check
		 * this before actually parsing the message, so that if there's a
		 * malformed response that's not ours, it doesn't confuse us.
		 */

		int id = ((in[0] & 0xFF) << 8) + (in[1] & 0xFF);

		int qid = query.getHeader().getID();

		if (id != qid) {
			String errorMessage = "Invalid message id, expected " + qid + " got " + id;
			throw new WireParseException(errorMessage);
		}

		Message response = parseMessage(in);

		return response;
	}

	/**
	 * Clean up.
	 */
	public void cleanUp() {
		sslClient.cleanUp();
	}
}
