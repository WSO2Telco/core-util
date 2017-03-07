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

    /**
     * The path finder host.
     */
    @XmlElement(name = "PathFinderHost", required = true)
    protected String pathFinderHost;

    /**
     * The port.
     */
    @XmlElement(name = "Port")
    protected int port;

    /**
     * The term domain.
     */
    @XmlElement(name = "TermDomain", required = true)
    protected String termDomain;

    /**
     * The mobile country codes.
     */
    @XmlElement(name = "MobileCountryCodes", required = true)
    protected MCCConfiguration.MobileCountryCodes mobileCountryCodes;


    /**
     * Gets the path finder host.
     *
     * @return the path finder host
     */
    public String getPathFinderHost() {
        return pathFinderHost;
    }


    /**
     * Sets the path finder host.
     *
     * @param value the new path finder host
     */
    public void setPathFinderHost(String value) {
        this.pathFinderHost = value;
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
     * @param value the new port
     */
    public void setPort(int value) {
        this.port = value;
    }


    /**
     * Gets the term domain.
     *
     * @return the term domain
     */
    public String getTermDomain() {
        return termDomain;
    }


    /**
     * Sets the term domain.
     *
     * @param value the new term domain
     */
    public void setTermDomain(String value) {
        this.termDomain = value;
    }


    /**
     * Gets the mobile country codes.
     *
     * @return the mobile country codes
     */
    public MCCConfiguration.MobileCountryCodes getMobileCountryCodes() {
        return mobileCountryCodes;
    }


    /**
     * Sets the mobile country codes.
     *
     * @param value the new mobile country codes
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

        /**
         * The mcc.
         */
        @XmlElement(name = "Mcc", required = true)
        protected List<MCCConfiguration.MobileCountryCodes.Mcc> mcc;


        /**
         * Gets the mcc.
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

            /**
             * The code.
             */
            @XmlElement(name = "Code")
            protected int code;

            /**
             * The calling code.
             */
            @XmlElement(name = "CallingCode", required = true)
            protected String callingCode;

            /**
             * The stage.
             */
            @XmlElement(name = "Stage")
            protected int stage;

            /**
             * The default mnc.
             */
            @XmlElement(name = "DefaultMnc", required = true)
            protected String defaultMnc;


            /**
             * Gets the code.
             *
             * @return the code
             */
            public int getCode() {
                return code;
            }


            /**
             * Sets the code.
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
             * Gets the stage.
             *
             * @return the stage
             */
            public int getStage() {
                return stage;
            }


            /**
             * Sets the stage.
             *
             * @param value the new stage
             */
            public void setStage(int value) {
                this.stage = value;
            }


            /**
             * Gets the default mnc.
             *
             * @return the default mnc
             */
            public String getDefaultMnc() {
                return defaultMnc;
            }


            /**
             * Sets the default mnc.
             *
             * @param value the new default mnc
             */
            public void setDefaultMnc(String value) {
                this.defaultMnc = value;
            }


        }

    }

}
