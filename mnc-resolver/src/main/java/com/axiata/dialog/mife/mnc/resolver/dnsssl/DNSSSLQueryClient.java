
package com.axiata.dialog.mife.mnc.resolver.dnsssl;

import com.axiata.dialog.mife.mnc.resolver.ConfigLoader;
import com.axiata.dialog.mife.mnc.resolver.dnsssl.SSLResolver;
import com.axiata.dialog.mife.mnc.resolver.dnsssl.RequestBean;
import com.axiata.dialog.mife.mnc.resolver.Configuration;
import com.axiata.dialog.mife.mnc.resolver.Configuration;
import com.axiata.dialog.mife.mnc.resolver.DataHolder;
import com.axiata.dialog.mife.mnc.resolver.IProviderNetwork;
import com.axiata.dialog.mife.mnc.resolver.MCCConfiguration;
import com.axiata.dialog.mife.mnc.resolver.MNCQueryClient;
import com.axiata.dialog.mife.mnc.resolver.dnsssl.DNSSSLBulkQuery;
import com.axiata.dialog.mife.mnc.resolver.dnsssl.DNSSSLQuery;
import com.axiata.dialog.mife.mnc.resolver.dnsssl.DNSQueryResult;
import com.axiata.dialog.mife.mnc.resolver.dnsssl.DNSResponseCode.RCODE;
import com.axiata.dialog.mife.mnc.resolver.dnsssl.SSLClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.utils.CarbonUtils;

/**
 * @author rrsharma
 * @version $Revision: 1.5 $
 *
 */
public class DNSSSLQueryClient implements IProviderNetwork {

    private static final String USAGE_BULK_QUERY = "Parameters required for Bulk Query :\n\t -i <inputfilepath> -o <outputfilepath>\n";
    private static final String USAGE_SINGLE_QUERY = "Parameters required for Single Query :\n\t -c <countrycode> -t <tn>\n";
    
    private static final Log log = LogFactory.getLog(DNSSSLQueryClient.class);

    /**
     * Method main. Entry point for the DNS query client
     *
     * @param args String[]
     */
    

    /**
     * Method performBulkQuery.
     *
     * @param inputFile The input file that contains country code and TN
     * @param outPutFile Qualified path for the output file where the results
     * would be saved
     * @param config Configuration object encapsulating host, port and
     * terminating domain information
     * @param sslResolver SSLResolver the SSL resolver
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
     * Method performSingleQuery.
     *
     * @param countryCode the country code
     * @param tn the TN
     * @param config Configuration object encapsulating host, port and
     * terminating domain information
     * @param sslResolver SSLResolver the SSL resolver
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

    @Override
    public String queryNetwork(String countryCode, String endUser) {
        SSLClient sslClient = new SSLClient();
        String TN = null;
        SSLResolver sslResolver = null;
        
        try {
            
            MCCConfiguration MCCconfig = DataHolder.getInstance().getMobileCountryConfig();
            System.out.println("host: "+MCCconfig.getPathFinderHost()+",port:"+MCCconfig.getPort()+",coutryCode:"+countryCode+",termDomain:"+MCCconfig.getTermDomain());
            sslClient.initialize(MCCconfig.getPathFinderHost(), MCCconfig.getPort());

            // Create SSLResolver and set host and port
            
            sslResolver = new SSLResolver(sslClient);
             // perform query for ingle TN
            log.debug("QueryNetwor, beforeperformSingleQuery: ");
            String tnResult = performSingleQuery(countryCode.replace("+", "").trim(), endUser.replace("+", "").trim(), MCCconfig, sslResolver);
            log.debug("QueryNetwor, tnsResult: "+tnResult);
            if ( (tnResult !=null) && (!tnResult.isEmpty()) ){
                TN=getEndpointMnc(tnResult);
            }
            
        } catch (Exception ioe) {
            System.err.println("Error while creating SSL socket");
            ioe.printStackTrace();            
        }
       
        return TN;
    }
    
    public String queryNetworkStandalone(String countryCode, String endUser) {
        SSLClient sslClient = new SSLClient();
        String TN = null;
        SSLResolver sslResolver = null;
        
        try {
            
            //DataHolder.getInstance().setMobileCountryConfig(ConfigLoader.getInstance().getMobileCountryConfig());
            String configPath = "MobileCountryConfig.xml";
            File file = new File(configPath);
            JAXBContext ctx = JAXBContext.newInstance(MCCConfiguration.class);
            Unmarshaller um = ctx.createUnmarshaller();
            
            MCCConfiguration MCCconfig = (MCCConfiguration) um.unmarshal(file);
            System.out.println("host: "+MCCconfig.getPathFinderHost()+",port:"+MCCconfig.getPort()+",coutryCode:"+countryCode+",termDomain:"+MCCconfig.getTermDomain());
            sslClient.initialize(MCCconfig.getPathFinderHost(), MCCconfig.getPort());

            // Create SSLResolver and set host and port
            sslResolver = new SSLResolver(sslClient);
             // perform query for ingle TN
            
            String tnResult = performSingleQuery(countryCode, endUser, MCCconfig, sslResolver);
            System.out.println("tnResult:"+tnResult);
            if ( (tnResult !=null) && (!tnResult.isEmpty()) ){
                TN=getEndpointMnc(tnResult);
            }
            
        } catch (Exception ioe) {
            System.err.println("Error while creating SSL socket");
            ioe.printStackTrace();            
        }
       
        return TN;
    }    
    
    public static String getEndpointMnc(String strMnc) {
        
        String mnc = null;
        
        Pattern p = Pattern.compile("(mnc=)(\\d+)");
        Matcher m = p.matcher(strMnc);
        while (m.find()) {
            mnc = m.group(2);           
        }
                
        return mnc;
    }
    
    
}
