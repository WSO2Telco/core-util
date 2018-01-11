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

    @XmlElementWrapper(name = "PremiumInfoScopes")
    @XmlElement(name = "Scope")
    protected List<Scope> premiumScopes;

    @XmlElementWrapper(name = "UserInfoScopes")
    @XmlElement(name = "Scope")
    protected List<Scope> userInfoScope;

    /**
     * Gets the premium info related scopes.
     *
     * @return the scope
     */
    public List<Scope> getPremiumScopes() {
        return premiumScopes;
    }

    /**
     * Sets the premium info related scope.
     *
     * @param premiumScopes the new scope related details
     */
    public void setPremiumScopes(List<Scope> premiumScopes) {
        this.premiumScopes = premiumScopes;
    }

    /**
     * Gets the user info related scope.
     *
     * @return the scope
     */
    public List<Scope> getUserInfoScope() {
        return userInfoScope;
    }

    /**
     * Sets the user info related scope.
     *
     * @param userInfoScope the new scope related details
     */
    public void setUserInfoScope(List<Scope> userInfoScope) {
        this.userInfoScope = userInfoScope;
    }

    /**
     * The Class Scope.
     */
    public static class Scope {

        private String name;
        private boolean isHashed;
        private List<String> claims;
        private List<String> displayAttributes;
        private List<MandatoryScope> mandatoryValues;

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
         * Gets the is Hashed value.
         *
         * @return the isHashed value
         */
        @XmlElement(name = "IsHashed")
        public boolean isHashed() {
            return isHashed;
        }

        /**
         * Sets the isHashed Value.
         *
         * @param hashed the new hashed value
         */
        public void setHashed(boolean hashed) {
            isHashed = hashed;
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
         * Gets the request Details-Mandatory Values.
         *
         * @return the request details -Mandatory scopes
         */
        @XmlElement(name = "MandatoryRequestClaimParams")
        public List<MandatoryScope> getMandatoryValues() {
            return mandatoryValues;
        }

        /**
         * Sets the request Details-Mandatory values.
         *
         * @param mandatoryValues the new Mandatory Scopes
         */
        public void setMandatoryValues(List<MandatoryScope> mandatoryValues) {
            this.mandatoryValues = mandatoryValues;
        }
    }

    /**
     * The Class MandatoryScope.
     */
    public static class MandatoryScope {

        private String name;
        private List<String> subset;

        /**
         * Gets name of the mandatory scopes.
         *
         * @return the name of the scope
         */
        @XmlElement(name = "Name")
        public String getName() {
            return name;
        }

        /**
         * Sets Name of the mandatory attribute in request.
         *
         * @param name the name of the scope
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Gets subValue  of the mandatory scopes.
         *
         * @return the subvalue of te mandatory attribute of the scope
         */
        @XmlElement(name = "SubValue")
        public List<String> getSubset() {
            return subset;
        }

        /**
         * Sets sub value of the mandatory attribute in request.
         *
         * @param subset the sub value of the scope
         */
        public void setSubset(List<String> subset) {
            this.subset = subset;
        }
    }
}
