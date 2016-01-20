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
import java.util.List;

import com.wso2telco.mnc.resolver.Configuration;
import com.wso2telco.mnc.resolver.MCCConfiguration;


// TODO: Auto-generated Javadoc
/**
 * Provides bulk query capability for SSL queries.
 *
 * @author dsingh
 * @version $Revision: 1.4 $
 */
public class DNSSSLBulkQuery {

	/**
	 * Method executeBulkQueries. executes bulk SSL queries
	 *
	 * @param queries            list of requests beans encapsulating country code and tn
	 * @param config            configuration object encapsulating host, port and terminating domain information
	 * @param sslResolver            the SSL resolver
	 * @return List of DNSQueryResult objects corresponding to list of queries
	 * @throws Exception the exception
	 */
	public List<DNSQueryResult> executeBulkQueries(
			final List<RequestBean> queries, final MCCConfiguration config,
			final SSLResolver sslResolver) throws Exception {

		ArrayList<DNSQueryResult> queryResults = new ArrayList<DNSQueryResult>();

		DNSSSLQuery dnsSSLQuery = new DNSSSLQuery();

		// Iterate over list of input queries and delegate each query to DNSSSLQuery instance
		for (RequestBean inputQuery : queries) {
			DNSQueryResult queryResult = dnsSSLQuery.execute(inputQuery.getCountryCode(), inputQuery.getTn(), config, sslResolver);
			queryResults.add(queryResult);
		}

		return queryResults;
	}
}
