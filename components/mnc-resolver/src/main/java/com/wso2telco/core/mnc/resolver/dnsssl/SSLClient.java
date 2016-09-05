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
package com.wso2telco.core.mnc.resolver.dnsssl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class SSLClient.
 */
public class SSLClient {

	/** The Constant OFFSET. */
	private static final int OFFSET = 0;

	/** The sslsocket. */
	private SSLSocket sslsocket;

	/** The sslis. */
	private InputStream sslis = null;

	/** The sslos. */
	private OutputStream sslos = null;

	 
	/**
	 * Initialize.
	 *
	 * @param host the host
	 * @param port the port
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws KeyManagementException the key management exception
	 */
	public void initialize(final String host, final int port)
			throws IOException, NoSuchAlgorithmException, KeyManagementException {
		SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

		if (sslsocket == null) {
        /*            
                    //temp to bypass proxy
                                    TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
                };

                // Install the all-trusting trust manager
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

                // Create all-trusting host name verifier
                HostnameVerifier allHostsValid = new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                };

                // Install the all-trusting host verifier
                //HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
                    
                   SSLSocketFactory socketFactory = sc.getSocketFactory(); 
  */
			synchronized (SSLClient.class) {

				if (sslsocket == null) {
					sslsocket = (SSLSocket) socketFactory.createSocket(host,port);
				}

				// Set the keep alive setting to true
				sslsocket.setKeepAlive(true);

				sslis = sslsocket.getInputStream();

				sslos = sslsocket.getOutputStream();
			}
		}
	}

	 
	/**
	 * Send.
	 *
	 * @param data the data
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void send(final byte[] data) throws IOException {
		byte[] byteArray = new byte[data.length + 2];
		byteArray[0] = (byte) (data.length >>> 8);
		byteArray[1] = (byte) (data.length & 0xFF);
		System.arraycopy(data, 0, byteArray, 2, data.length);
		sslos.write(byteArray);
		sslos.flush();
	}

	 
	/**
	 * Recv buffer.
	 *
	 * @param length the length
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private byte[] recvBuffer(final int length) throws IOException {

		byte[] data = new byte[length];
		sslis.read(data, OFFSET, data.length - OFFSET);
		return data;
	}

	 
	/**
	 * Recv.
	 *
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private byte[] recv() throws IOException {

		byte[] buf = recvBuffer(2);
		int length = ((buf[0] & 0xFF) << 8) + (buf[1] & 0xFF);
		byte[] data = recvBuffer(length);

		return data;
	}

	 
	/**
	 * Sendrecv.
	 *
	 * @param data the data
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public byte[] sendrecv(final byte[] data) throws IOException {
		send(data);
		return recv();

	}

	// clean up the resources, close
	// the socket input/output streams
	/**
	 * Clean up.
	 */
	// and socket
	public void cleanUp() {
		try {
			if (sslis != null) {
				sslis.close();
			}
			if (sslos != null) {
				sslos.close();
			}
			if (sslsocket != null) {
				sslis.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
