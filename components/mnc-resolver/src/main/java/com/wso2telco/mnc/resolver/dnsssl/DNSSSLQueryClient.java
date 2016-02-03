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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wso2telco.mnc.resolver.Configuration;
import com.wso2telco.mnc.resolver.DataHolder;
import com.wso2telco.mnc.resolver.IProviderNetwork;
import com.wso2telco.mnc.resolver.MCCConfiguration;
import com.wso2telco.mnc.resolver.dnsssl.DNSQueryResult;
import com.wso2telco.mnc.resolver.dnsssl.DNSSSLBulkQuery;
import com.wso2telco.mnc.resolver.dnsssl.DNSSSLQuery;
import com.wso2telco.mnc.resolver.dnsssl.RequestBean;
import com.wso2telco.mnc.resolver.dnsssl.SSLClient;
import com.wso2telco.mnc.resolver.dnsssl.SSLResolver;
import com.wso2telco.mnc.resolver.dnsssl.DNSResponseCode.RCODE;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

 
// TODO: Auto-generated Javadoc
/**
 * The Class DNSSSLQueryClient.
 */
public class DNSSSLQueryClient implements IProviderNetwork {

    /** The Constant USAGE_BULK_QUERY. */
    private static final String USAGE_BULK_QUERY = "Parameters required for Bulk Query :\n\t -i <inputfilepath> -o <outputfilepath>\n";
    
    /** The Constant USAGE_SINGLE_QUERY. */
    private static final String USAGE_SINGLE_QUERY = "Parameters required for Single Query :\n\t -c <countrycode> -t <tn>\n";

     
    

     
    /**
     * Perform bulk query.
     *
     * @param inputFile the input file
     * @param outPutFile the out put file
     * @param config the config
     * @param sslResolver the ssl resolver
     */
    private static void performBulkQuery(final String inputFile,
            final String outPutFile, final MCCConfiguration config,
            final SSLResolver sslResolver) {

        List<DNSQueryResult> queriesResults = new ArrayList<DNSQueryResult>();

        List<RequestBean> queriesRequest = new ArrayList<RequestBean>();

        RequestBean request = null;

        String dataStr = null;

        BufferedReader reader = null;

        Writer writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));

            // read the list of queries from input file
            while ((dataStr = reader.readLine()) != null) {

                System.out.println("Processing line - " + dataStr);

                String[] fields = dataStr.split(",");

                if (fields.length > 1) {
                    request = new RequestBean();
                    request.setCountryCode(fields[0]);
                    request.setTn(fields[1]);
                    queriesRequest.add(request);
                } else {
                    System.err.print("Invalid line in input file - " + dataStr);
                }
            }

            DNSSSLBulkQuery dnsSSLBulkQuery = new DNSSSLBulkQuery();

            queriesResults = dnsSSLBulkQuery.executeBulkQueries(queriesRequest,
                    config, sslResolver);

            Iterator<DNSQueryResult> resultIterator = queriesResults
                    .listIterator();

            writer = new PrintWriter(new FileWriter(outPutFile));

            List<String> naptrArray = new ArrayList<String>();

            Iterator<String> naptIt = naptrArray.listIterator();

            while (resultIterator.hasNext()) {
                DNSQueryResult result = resultIterator.next();

                if (result != null) {
                    writer.write(result.getCountryCode());
                    writer.write(" | ");
                    writer.write(result.getTn());
                    writer.write(" | ");
                    if (result.getRcode() == RCODE.REFUSED) {
                        writer.write(" REFUSED ");
                        writer.write("\n");
                    } else if (result.getRcode() == RCODE.NXDOMAIN) {
                        writer.write(" NXDOMAIN ");
                        writer.write("\n");
                    } else if (result.getRcode() == RCODE.FORMAT_ERROR) {
                        writer.write(" FORMAT_ERROR ");
                        writer.write("\n");
                    } else if (result.getRcode() == RCODE.SERVFAIL) {
                        writer.write(" SERVFAIL ");
                        writer.write("\n");
                    } else if (result.getRcode() == RCODE.SERVER_NOT_FOUND) {
                        writer.write(" SERVER_NOT_FOUND ");
                        writer.write("\n");
                    } else if (result.getRcode() == RCODE.NO_ERROR) {
                        writer.write("TN match success. Route = ");
                        naptrArray = result.getNaptrArray();
                        naptIt = naptrArray.listIterator();
                        while (naptIt.hasNext()) {
                            String naptrResult = naptIt.next();
                            writer.write(" | " + naptrResult);
                        }
                        writer.write("\n\n");
                    } else {
                        writer.write("Unanticipated error: "
                                + result.getRcode()
                                + ".Please contact NeuStar customer support");
                        writer.write("\n");
                    }
                }
            }
            writer.flush();
        } catch (Exception e) {
            System.err.println("Exception occured while sending bulk query");
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.err
                        .println("Problem occured while closing output stream.");
            }
        }
    }

     
    /**
     * Perform single query.
     *
     * @param countryCode the country code
     * @param tn the tn
     * @param config the config
     * @param sslResolver the ssl resolver
     * @return the string
     */
    private static String performSingleQuery(final String countryCode, final String tn,
            final MCCConfiguration config, final SSLResolver sslResolver) {

        String TN = "";
        try {

            DNSSSLQuery dnsSSLQuery = new DNSSSLQuery();

            DNSQueryResult queryResult = dnsSSLQuery.execute(countryCode, tn, config, sslResolver);

            List<String> naptrArray = new ArrayList<String>();

            Iterator<String> naptIt = naptrArray.listIterator();

            if ((queryResult != null) && (queryResult.getRcode() == RCODE.REFUSED)) {
                System.err
                        .println("Got refused response from server.\n "
                        + "Please contact NeuStar customer support, "
                        + "with the IP address and X.509 cert of the client machine");
            } else if (queryResult.getRcode() == RCODE.NXDOMAIN) {
                System.err
                        .println("TN not found.\n"
                        + " Please verify with NeuStar customer support that "
                        + "the terminating domain setting above matches the profile configuration");
            } else if (queryResult.getRcode() == RCODE.FORMAT_ERROR) {
                System.err
                        .println("The query format appears to be incorrect,\n"
                        + " please verify whether you are using correct ENUM format for queries");

            } else if (queryResult.getRcode() == RCODE.SERVFAIL) {
                System.err
                        .println("There was an internal server error which caused a failure to respond\n"
                        + " Please report to NeuStar customer support.");

            } else if (queryResult.getRcode() == RCODE.SERVER_NOT_FOUND) {
                System.err
                        .println("Server could not be found, please check host name and port.");
            } else if (queryResult.getRcode() == RCODE.NO_ERROR) {
                System.out.println("TN match success. Route = ");
                naptrArray = queryResult.getNaptrArray();
                naptIt = naptrArray.listIterator();
                while (naptIt.hasNext()) {
                    String naptrResult = naptIt.next();
                    TN += " | " + naptrResult;
                }
            } else {
                System.err.println("Unanticipated error: "
                        + queryResult.getRcode()
                        + ". Please contact NeuStar customer support");
            }

            // perform the clean up
            sslResolver.cleanUp();

        } catch (Exception e) {
            System.err.println("Exception occured while sending single query");
            e.printStackTrace();
        }
        return TN;
    }

    /* (non-Javadoc)
     * @see com.wso2telco.mnc.resolver.IProviderNetwork#queryNetwork(java.lang.String, java.lang.String)
     */
    @Override
    public String queryNetwork(String countryCode, String endUser) {
        SSLClient sslClient = new SSLClient();
        String TN = null;
        SSLResolver sslResolver = null;
        
        try {
            
            MCCConfiguration MCCconfig = DataHolder.getInstance().getMobileCountryConfig();
            sslClient.initialize(MCCconfig.getPathFinderHost(), MCCconfig.getPort());

            // Create SSLResolver and set host and port
            sslResolver = new SSLResolver(sslClient);
             // perform query for ingle TN
            TN = performSingleQuery(countryCode, endUser, MCCconfig, sslResolver);
        } catch (Exception ioe) {
            System.err.println("Error while creating SSL socket");
            ioe.printStackTrace();            
        }
       
        return null;
    }
    
    
}
