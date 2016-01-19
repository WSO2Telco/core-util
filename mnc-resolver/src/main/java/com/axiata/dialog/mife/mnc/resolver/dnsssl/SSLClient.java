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



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author dsingh
 * 
 * @version $Revision: 1.4 $
 */

public class SSLClient {

	private static final int OFFSET = 0;

	private SSLSocket sslsocket;

	private InputStream sslis = null;

	private OutputStream sslos = null;

	/**
	 * Method initialize. initialize the SSL client creates SSL socket and sets
	 * keep alive to true
	 * 
	 * @param host
	 *            DNS server host
	 * @param port
	 *            port for DNS server
	 * @throws IOException
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
	 * Method send. Sends the message
	 * 
	 * @param data
	 *            message passed as byte array
	 * @throws IOException
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
	 * Method recvBuffer. Utility method to receive the message called internally by
	 * recv method to read the length of message first and then the message
	 * itself
	 * 
	 * @param length
	 *            length of message
	 * @return byte[] message as byte array
	 * @throws IOException
	 */
	private byte[] recvBuffer(final int length) throws IOException {

		byte[] data = new byte[length];
		sslis.read(data, OFFSET, data.length - OFFSET);
		return data;
	}

	/**
	 * Method recv. Receive the message
	 * 
	 * @return byte[] message as byte array
	 * @throws IOException
	 */
	private byte[] recv() throws IOException {

		byte[] buf = recvBuffer(2);
		int length = ((buf[0] & 0xFF) << 8) + (buf[1] & 0xFF);
		byte[] data = recvBuffer(length);

		return data;
	}

	/**
	 * Method sendrecv. Send and receive the message
	 * 
	 * @param data
	 *            byte[] message to send as byte array
	 * @return byte[] received message as byte array
	 * @throws IOException
	 */
	public byte[] sendrecv(final byte[] data) throws IOException {
		send(data);
		return recv();

	}

	// clean up the resources, close
	// the socket input/output streams
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
