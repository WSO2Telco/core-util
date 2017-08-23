/*******************************************************************************
 * Copyright (c) 2015-2017, WSO2.Telco Inc. (http://www.wso2telco.com)
 *
 * All Rights Reserved. WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
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
package com.wso2telco.core.config.model;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * The Class Scope.
 */
@XmlRootElement(name = "ScopeConfigs")
@XmlAccessorType(XmlAccessType.FIELD)
public class ScopeDetailsConfig {

    @XmlElementWrapper(name = "Scopes")
    @XmlElement(name = "Scope")
    protected List<Scope> scope;

    /**
     * Gets the scope.
     *
     * @return the scope
     */
    public List<Scope> getScope() {
        return scope;
    }

    /**
     * Sets the scope.
     *
     * @param scope the new scope related details
     */
    public void setScope(List<Scope> scope) {
        this.scope = scope;
    }

    /**
     * The Class Scope.
     */
    public static class Scope {

        private String name;
        private List<String> claims;
        private List<String> optionalValues;
        private List<String> mandatoryValues;
        private List<String> displayAttributes;

        /**
         * Gets the scope name.
         *
         * @return the scope name
         */
        @XmlElement(name = "Name")
        public String getName() {
            return name;
        }

        /**
         * Sets the scope name.
         *
         * @param scopeName the new data source name
         */
        public void setName(String scopeName) {
            this.name = scopeName;
        }

        /**
         * Gets the claim values.
         *
         * @return the claim values list
         */
        @XmlElementWrapper(name = "Claims")
        @XmlElement(name = "ClaimValue")
        public List<String> getClaimSet() {
            return claims;
        }

        /**
         * Sets the claims.
         *
         * @param claims the new claim list
         */
        public void setClaimSet(List<String> claims) {
            this.claims = claims;
        }

        /**
         * Gets scope related display attributes.
         *
         * @return the display attribute list
         */

        @XmlElementWrapper(name = "AttributeToDisplay")
        @XmlElement(name = "Attribute")
        public List<String> getDisplayAttributes() {
            return displayAttributes;
        }

        /**
         * Sets the display attribute list.
         *
         * @param displayAttributes the new display attribute list for a specific scope.
         */
        public void setDisplayAttributes(List<String> displayAttributes) {
            this.displayAttributes = displayAttributes;
        }

        /**
         * Gets the request Details-Optional Values.
         *
         * @return the request details list
         */

        @XmlElementWrapper(name = "Request")
        @XmlElement(name = "Optional")
        public List<String> getOptionalValues() {
            return optionalValues;
        }

        /**
         * Gets the request Details-Mandatory Values.
         *
         * @return the request details -Mandatory scopes
         */
        @XmlElementWrapper(name = "Request")
        @XmlElement(name = "Mandatory")
        public List<String> getMandatoryValues() {
            return mandatoryValues;
        }

        /**
         * Sets the request Details-Optional values.
         *
         * @param optionalValues the new Optional scopes
         */
        public void setOptionalValues(List<String> optionalValues) {
            this.optionalValues = optionalValues;
        }

        /**
         * Sets the request Details-Mandatory values.
         *
         * @param mandatoryValues the new Mandatory Scopes
         */
        public void setMandatoryValues(List<String> mandatoryValues) {
            this.mandatoryValues = mandatoryValues;
        }

    }
}
