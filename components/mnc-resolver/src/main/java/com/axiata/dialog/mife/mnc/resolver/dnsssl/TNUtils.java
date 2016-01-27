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



/**
 * 
 * Utility class for providing functions to transform the TN to a
 * domain name (e164 format) Please refer RFC 3761
 * 
 * For instance, if term domain is "e164enum.net", TN "+15714345400" will
 * be transformed to 0.0.4.5.4.3.4.1.7.5.1.e164enum.net
 * 
 * @author rrsharma
 * 
 * @version $Revision: 1.3 $
 */
public class TNUtils {

	/**
	 * Method getE164TN.
	 * @param cc The country code
	 * @param tn The TN
	 * @param termDomain The terminating domain
	 * @return e164 representation for this TN, including the country code and terminating domain
	 */
	public static String getE164TN(final String cc, final String tn,
			final String termDomain) {

		StringBuffer out = new StringBuffer();

		char []tnChars = tn.toCharArray();

		for(int idx = tnChars.length - 1; idx >= 0; idx--) {
			out.append(tnChars[idx]).append(".");
		}

		char []ccChars = cc.toCharArray();

		for(int idx = ccChars.length - 1; idx >= 0; idx--) {
			out.append(ccChars[idx]).append(".");
		}

		out.append(termDomain);

		return out.toString();
	}
}
