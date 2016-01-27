package com.axiata.dialog.mife.mnc.resolver.dnsssl;

/*
 * Copyright (c) 2013 Neustar, Inc.  All Rights Reserved.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL NEUSTAR BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */



import com.axiata.dialog.mife.mnc.resolver.Configuration;
import com.axiata.dialog.mife.mnc.resolver.MCCConfiguration;
import java.util.ArrayList;
import java.util.List;


/**
 * Provides bulk query capability for SSL queries
 * 
 * @author dsingh
 * @version $Revision: 1.4 $
 */
public class DNSSSLBulkQuery {

	/**
	 * Method executeBulkQueries. executes bulk SSL queries
	 * 
	 * @param queries
	 *            list of requests beans encapsulating country code and tn
	 * @param config
	 *            configuration object encapsulating host, port and terminating domain information
	 * @param sslResolver
	 *            the SSL resolver
	 * @return List of DNSQueryResult objects corresponding to list of queries
	 * @throws Exception
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
