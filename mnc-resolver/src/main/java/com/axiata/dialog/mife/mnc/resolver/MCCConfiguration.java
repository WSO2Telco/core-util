/*
 * IProviderNetwork.java
 * Nov 17, 2014  11:00:37 AM
 * Roshan.Saputhanthri
 *
 * Copyright (C) Dialog Axiata PLC. All Rights Reserved.
 */

package com.axiata.dialog.mife.mnc.resolver;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pathFinderHost",
    "port",
    "termDomain",
    "mobileCountryCodes"
})
@XmlRootElement(name = "MCCConfiguration")
public class MCCConfiguration {

    @XmlElement(name = "PathFinderHost", required = true)
    protected String pathFinderHost;
    @XmlElement(name = "Port")
    protected int port;
    @XmlElement(name = "TermDomain", required = true)
    protected String termDomain;
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
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the value of the port property.
     * 
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

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "mcc"
    })
    public static class MobileCountryCodes {

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
         * 
         */
        public List<MCCConfiguration.MobileCountryCodes.Mcc> getMcc() {
            if (mcc == null) {
                mcc = new ArrayList<MCCConfiguration.MobileCountryCodes.Mcc>();
            }
            return this.mcc;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "code",
            "callingCode",
            "stage",
            "defaultMnc"
        })
        public static class Mcc {

            @XmlElement(name = "Code")
            protected int code;
            @XmlElement(name = "CallingCode", required = true)
            protected String callingCode;           
            @XmlElement(name = "Stage")
            protected int stage;
            @XmlElement(name = "DefaultMnc", required = true)
            protected String defaultMnc;

            /**
             * Gets the value of the code property.
             * 
             */
            public int getCode() {
                return code;
            }

            /**
             * Sets the value of the code property.
             * 
             */
            public void setCode(int value) {
                this.code = value;
            }

            public String getCallingCode() {
                return callingCode;
            }

            public void setCallingCode(String callingCode) {
                this.callingCode = callingCode;
            }
            
            /**
             * Gets the value of the stage property.
             * 
             */
            public int getStage() {
                return stage;
            }

            /**
             * Sets the value of the stage property.
             * 
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
