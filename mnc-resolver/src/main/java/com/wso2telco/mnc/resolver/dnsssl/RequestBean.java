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
 * Encapsulates the query request fields like Country code and TN.
 *
 * @author dsingh
 * @version $Revision: 1.1 $
 */

public class RequestBean {

	/** The country code. */
	private String countryCode;

	/** The tn. */
	private String tn;

	/**
	 * Method toString.
	 * @return String
	 */
	@Override
	public String toString() {
		return "RequestBean [countryCode = " + countryCode + ", TN = " + tn
				+ "]";
	}

	/**
	 * Method getCountryCode.
	 * @return the country code
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Method setCountryCode.
	 * @param countryCode the country code
	 */
	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Method getTn.
	 * @return the TN
	 */
	public String getTn() {
		return tn;
	}

	/**
	 * Method setTn.
	 * @param number the TN
	 */
	public void setTn(final String number) {
		this.tn = number;
	}
}
