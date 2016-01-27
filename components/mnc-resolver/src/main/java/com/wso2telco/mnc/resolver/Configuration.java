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
package com.wso2telco.mnc.resolver;

 



import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

 
// TODO: Auto-generated Javadoc
/**
 * The Class Configuration.
 */
public class Configuration {

	/** The Constant PROPERTY_FILE_PATH. */
	private static final String PROPERTY_FILE_PATH = "conf/sslclient.properties";

	/** The Constant HOST. */
	private static final String HOST = "host";

	/** The Constant PORT. */
	private static final String PORT = "port";

	/** The Constant TERM_DOMAIN. */
	private static final String TERM_DOMAIN = "termDomain";

	/** The host. */
	private String host;

	/** The port. */
	private int port;

	/** The term domain. */
	private String termDomain;

	/** The config properties. */
	private Properties configProperties;

	/**
	 * Instantiates a new configuration.
	 */
	public Configuration() {
	}

	 
	/**
	 * Initialize.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void initialize() throws IOException {
		configProperties = new Properties();
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(PROPERTY_FILE_PATH);

			configProperties.load(inStream);

			host = configProperties.getProperty(HOST);

			port = Integer.valueOf(configProperties.getProperty(PORT));

			termDomain = configProperties.getProperty(TERM_DOMAIN);

		} finally {
			if (inStream != null) {
				inStream.close();
			}
		}
	}

	 
	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	 
	/**
	 * Sets the host.
	 *
	 * @param host the new host
	 */
	public void setHost(final String host) {
		this.host = host;
	}

	 
	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	 
	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(final int port) {
		this.port = port;
	}

	 
	/**
	 * Gets the term domain.
	 *
	 * @return the term domain
	 */
	public String getTermDomain() {
		return termDomain;
	}

	 
	/**
	 * Sets the term domain.
	 *
	 * @param termDomain the new term domain
	 */
	public void setTermDomain(final String termDomain) {
		this.termDomain = termDomain;
	}
}
