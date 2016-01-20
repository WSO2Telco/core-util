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

import java.util.ArrayList;

import org.xbill.DNS.DClass;
import org.xbill.DNS.Message;
import org.xbill.DNS.Name;
import org.xbill.DNS.Record;
import org.xbill.DNS.Section;
import org.xbill.DNS.Type;

import com.wso2telco.mnc.resolver.Configuration;
import com.wso2telco.mnc.resolver.MCCConfiguration;


// TODO: Auto-generated Javadoc
/**
 * Used to query the NeuStar PathFinder Service to
 * obtain carrier information for a given TN over SSL
 * <p>.
 *
 * @author Chandan Gupta
 * @version $Revision: 1.4 $
 */

public class DNSSSLQuery implements DNSResponseCode {

	/**
	 * Execute method which demonstrates usage of SSLResolver to retrieve ENUM
	 * results
	 * <p>.
	 *
	 * @param countryCode            the country code
	 * @param tn            the TN
	 * @param config            Configuration object encapsulating host, port and terminating domain information
	 * @param sslResolver            SSLResolver the SSL resolver
	 * @return DNSQueryResult Query result object that encapsulates the query results
	 * @throws Exception the exception
	 */
	public DNSQueryResult execute(final String countryCode,
			final String tn, final MCCConfiguration config,
			final SSLResolver sslResolver)
					throws Exception {

		DNSQueryResult queryResult = new DNSQueryResult();

		// Verify that tn and countryCode are numeric
		try {
			Integer.parseInt(countryCode);
			Long.parseLong(tn);
		} catch (NumberFormatException ne) {
			System.err.println("TN or country code should be numeric");
			throw new NumberFormatException("TN or country code should be numeric");
		}

		// transform the TN to a domain name; RFC3761
		// For instance, "+15714345400" will be transformed to
		// "0.0.4.5.4.3.4.1.7.5.1.<Terminating domain>"
		String e164TN = TNUtils.getE164TN(countryCode,
				tn, config.getTermDomain());

		if (e164TN != null) {

			try {

				// Create Name from String
				Name name = Name.fromString(e164TN, Name.root);

				Record record = Record.newRecord(name, Type.NAPTR, DClass.IN);

				Message query = Message.newQuery(record);

				Message response = sslResolver.send(query);

				queryResult.setMessageId(response.getHeader().getID());

				// Parse the response code to identify whether an error has
				// occurred and populate the response
				switch (response.getRcode()) {
				case 0:
					queryResult.setRcode(DNSResponseCode.RCODE.NO_ERROR);
					Record[] answers = response.getSectionArray(Section.ANSWER);

					// For most of the services a single NAPTR record is
					// returned multiple NAPTR records are expected,
					// navigate through the answers

					int i = 0;
					ArrayList<String> naptrArray = new ArrayList<String>();
					while ((answers.length > 0) && (i < answers.length)) {
						naptrArray.add(answers[i].rdataToString());
						i++;
					}
					queryResult.setNaptrArray(naptrArray);
					break;
				case 1:
					queryResult.setRcode(DNSResponseCode.RCODE.FORMAT_ERROR);
					break;
				case 2:
					queryResult.setRcode(DNSResponseCode.RCODE.SERVFAIL);
					break;
				case 3:
					queryResult.setRcode(DNSResponseCode.RCODE.NXDOMAIN);
					break;
				case 4:
					queryResult.setRcode(DNSResponseCode.RCODE.IMPL_ERROR);
					break;
				case 5:
					queryResult.setRcode(DNSResponseCode.RCODE.REFUSED);
					break;
				default:
					queryResult.setRcode(DNSResponseCode.RCODE.UNANTICIPATED);
				}

			}  catch (Exception e) {
				System.err.println("Error occured in bulk queries flow ");
				e.printStackTrace();
				queryResult.setRcode(RCODE.UNANTICIPATED);
			}
		} else {
			System.err.println("Error in parsing the number: CC["
					+ countryCode + "] Number["
					+ tn + "]");
		}

		queryResult.setCountryCode(countryCode);

		queryResult.setTn(tn);

		return queryResult;
	}
}
