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

// TODO: Auto-generated Javadoc
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
