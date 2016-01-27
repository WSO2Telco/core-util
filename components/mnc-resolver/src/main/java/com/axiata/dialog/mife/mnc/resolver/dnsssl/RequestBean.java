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
 * Encapsulates the query request fields like Country code and TN
 * 
 * @author dsingh
 * 
 * @version $Revision: 1.1 $
 */

public class RequestBean {

	private String countryCode;

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
