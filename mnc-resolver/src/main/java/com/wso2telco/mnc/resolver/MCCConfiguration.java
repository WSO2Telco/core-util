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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


// TODO: Auto-generated Javadoc
/**
 * The Class MCCConfiguration.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pathFinderHost",
    "port",
    "termDomain",
    "mobileCountryCodes"
})
@XmlRootElement(name = "MCCConfiguration")
public class MCCConfiguration {

    /** The path finder host. */
    @XmlElement(name = "PathFinderHost", required = true)
    protected String pathFinderHost;
    
    /** The port. */
    @XmlElement(name = "Port")
    protected int port;
    
    /** The term domain. */
    @XmlElement(name = "TermDomain", required = true)
    protected String termDomain;
    
    /** The mobile country codes. */
    @XmlElement(name = "MobileCountryCodes", required = true)
    protected MCCConfiguration.MobileCountryCodes mobileCountryCodes;

    /**
     * Gets the value of the pathFinderHost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPathFinderHost() {
        return pathFinderHost;
    }

    /**
     * Sets the value of the pathFinderHost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPathFinderHost(String value) {
        this.pathFinderHost = value;
    }

    /**
     * Gets the value of the port property.
     *
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the value of the port property.
     *
     * @param value the new port
     */
    public void setPort(int value) {
        this.port = value;
    }

    /**
     * Gets the value of the termDomain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermDomain() {
        return termDomain;
    }

    /**
     * Sets the value of the termDomain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermDomain(String value) {
        this.termDomain = value;
    }

    /**
     * Gets the value of the mobileCountryCodes property.
     * 
     * @return
     *     possible object is
     *     {@link MCCConfiguration.MobileCountryCodes }
     *     
     */
    public MCCConfiguration.MobileCountryCodes getMobileCountryCodes() {
        return mobileCountryCodes;
    }

    /**
     * Sets the value of the mobileCountryCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link MCCConfiguration.MobileCountryCodes }
     *     
     */
    public void setMobileCountryCodes(MCCConfiguration.MobileCountryCodes value) {
        this.mobileCountryCodes = value;
    }

    /**
     * The Class MobileCountryCodes.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "mcc"
    })
    public static class MobileCountryCodes {

        /** The mcc. */
        @XmlElement(name = "Mcc", required = true)
        protected List<MCCConfiguration.MobileCountryCodes.Mcc> mcc;

        /**
         * Gets the value of the mcc property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the mcc property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMcc().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MCCConfiguration.MobileCountryCodes.Mcc }
         *
         * @return the mcc
         */
        public List<MCCConfiguration.MobileCountryCodes.Mcc> getMcc() {
            if (mcc == null) {
                mcc = new ArrayList<MCCConfiguration.MobileCountryCodes.Mcc>();
            }
            return this.mcc;
        }

        /**
         * The Class Mcc.
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "code",
            "callingCode",
            "stage",
            "defaultMnc"
        })
        public static class Mcc {

            /** The code. */
            @XmlElement(name = "Code")
            protected int code;
            
            /** The calling code. */
            @XmlElement(name = "CallingCode", required = true)
            protected String callingCode;           
            
            /** The stage. */
            @XmlElement(name = "Stage")
            protected int stage;
            
            /** The default mnc. */
            @XmlElement(name = "DefaultMnc", required = true)
            protected String defaultMnc;

            /**
             * Gets the value of the code property.
             *
             * @return the code
             */
            public int getCode() {
                return code;
            }

            /**
             * Sets the value of the code property.
             *
             * @param value the new code
             */
            public void setCode(int value) {
                this.code = value;
            }

            /**
             * Gets the calling code.
             *
             * @return the calling code
             */
            public String getCallingCode() {
                return callingCode;
            }

            /**
             * Sets the calling code.
             *
             * @param callingCode the new calling code
             */
            public void setCallingCode(String callingCode) {
                this.callingCode = callingCode;
            }
            
            /**
             * Gets the value of the stage property.
             *
             * @return the stage
             */
            public int getStage() {
                return stage;
            }

            /**
             * Sets the value of the stage property.
             *
             * @param value the new stage
             */
            public void setStage(int value) {
                this.stage = value;
            }

            /**
             * Gets the value of the defaultMnc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDefaultMnc() {
                return defaultMnc;
            }

            /**
             * Sets the value of the defaultMnc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDefaultMnc(String value) {
                this.defaultMnc = value;
            }
            
            
            
        }

    }

}
