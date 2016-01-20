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



import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * Configuration object encapsulating the properties like
 * host, port, terminating domain and number of threads.
 *
 * @author rrsharma
 * @version $Revision: 1.1 $
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
	 * Method initialize.
	 * 		Initializes the configuration object after properties
	 * 		from property file
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
	 * @param host            the host to set
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
	 * @param port            the port to set
	 */
	public void setPort(final int port) {
		this.port = port;
	}

	/**
	 * Gets the term domain.
	 *
	 * @return the termDomain
	 */
	public String getTermDomain() {
		return termDomain;
	}

	/**
	 * Sets the term domain.
	 *
	 * @param termDomain            the termDomain to set
	 */
	public void setTermDomain(final String termDomain) {
		this.termDomain = termDomain;
	}
}
