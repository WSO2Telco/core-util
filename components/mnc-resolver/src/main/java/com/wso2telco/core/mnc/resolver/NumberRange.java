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
package com.wso2telco.core.mnc.resolver;

 
// TODO: Auto-generated Javadoc
/**
 * The Class NumberRange.
 */
public class NumberRange {

    /** The rangefrom. */
    long rangefrom;
    
    /** The rangeto. */
    long rangeto;    
    
    /** The mnccode. */
    String mnccode;
    
    /** The brand. */
    String brand;

    /**
     * Instantiates a new number range.
     *
     * @param rangefrom the rangefrom
     * @param rangeto the rangeto
     * @param mnccode the mnccode
     * @param brand the brand
     */
    public NumberRange(long rangefrom, long rangeto, String mnccode, String brand) {
        this.rangefrom = rangefrom;
        this.rangeto = rangeto;
        this.mnccode = mnccode;
        this.brand = brand;
    }
    
    /**
     * Gets the rangefrom.
     *
     * @return the rangefrom
     */
    public long getRangefrom() {
        return rangefrom;
    }

    /**
     * Sets the rangefrom.
     *
     * @param rangefrom the new rangefrom
     */
    public void setRangefrom(long rangefrom) {
        this.rangefrom = rangefrom;
    }

    /**
     * Gets the rangeto.
     *
     * @return the rangeto
     */
    public long getRangeto() {
        return rangeto;
    }

    /**
     * Sets the rangeto.
     *
     * @param rangeto the new rangeto
     */
    public void setRangeto(long rangeto) {
        this.rangeto = rangeto;
    }

    /**
     * Gets the mnccode.
     *
     * @return the mnccode
     */
    public String getMnccode() {
        return mnccode;
    }

    /**
     * Sets the mnccode.
     *
     * @param mnccode the new mnccode
     */
    public void setMnccode(String mnccode) {
        this.mnccode = mnccode;
    }

    /**
     * Gets the brand.
     *
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand.
     *
     * @param brand the new brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
}
