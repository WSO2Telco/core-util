package com.axiata.dialog.mife.mnc.resolver;

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

/**
 * Configuration object encapsulating the properties like
 * host, port, terminating domain and number of threads
 * 
 * @author rrsharma
 * 
 * @version $Revision: 1.1 $
 */
public class Configuration {

	private static final String PROPERTY_FILE_PATH = "conf/sslclient.properties";

	private static final String HOST = "host";

	private static final String PORT = "port";

	private static final String TERM_DOMAIN = "termDomain";

	private String host;

	private int port;

	private String termDomain;

	private Properties configProperties;

	public Configuration() {
	}

	/**
	 * Method initialize.
	 * 		Initializes the configuration object after properties
	 * 		from property file
	 * @throws IOException
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

	 * @return the host */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(final String host) {
		this.host = host;
	}

	/**

	 * @return the port */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(final int port) {
		this.port = port;
	}

	/**

	 * @return the termDomain */
	public String getTermDomain() {
		return termDomain;
	}

	/**
	 * @param termDomain
	 *            the termDomain to set
	 */
	public void setTermDomain(final String termDomain) {
		this.termDomain = termDomain;
	}
}
