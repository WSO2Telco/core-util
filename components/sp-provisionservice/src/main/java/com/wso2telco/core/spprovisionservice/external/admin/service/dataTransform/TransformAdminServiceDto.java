/*******************************************************************************
 * Copyright  (c) 2015-2017, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
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
package com.wso2telco.core.spprovisionservice.external.admin.service.dataTransform;

import com.wso2telco.core.spprovisionservice.sp.entity.AdminServiceDto;
import org.wso2.carbon.identity.oauth.stub.dto.OAuthConsumerAppDTO;

/**
 * This class transform AdminService data to OAuthConsumerAppDTO in the org.wso2.carbon.identity.oauth.stub.dto
 */
public class TransformAdminServiceDto {

    OAuthConsumerAppDTO oAuthConsumerAppDto = null;
    AdminServiceDto adminServiceDto = null;

    public OAuthConsumerAppDTO transformToOAuthConsumerAppDto(AdminServiceDto adminServiceDto) {

        oAuthConsumerAppDto = new OAuthConsumerAppDTO();
        oAuthConsumerAppDto.setApplicationName(adminServiceDto.getApplicationName());
        oAuthConsumerAppDto.setOAuthVersion(adminServiceDto.getOauthVersion());
        oAuthConsumerAppDto.setCallbackUrl(adminServiceDto.getCallbackUrl());
        oAuthConsumerAppDto.setGrantTypes(adminServiceDto.getGrantTypes());
        oAuthConsumerAppDto.setOauthConsumerKey(adminServiceDto.getOauthConsumerKey());

        if (adminServiceDto.getOauthConsumerSecret() != null) {
            oAuthConsumerAppDto.setOauthConsumerSecret(adminServiceDto.getOauthConsumerSecret());
        } else {
            oAuthConsumerAppDto.setOauthConsumerSecret(randomSecretKeyGenerator().toString());
        }
        oAuthConsumerAppDto.setPkceMandatory(adminServiceDto.isPkceMandatory());
        oAuthConsumerAppDto.setPkceSupportPlain(adminServiceDto.isPkceSupportPlain());

        return oAuthConsumerAppDto;
    }
    
    public AdminServiceDto transformToOAuthConsumerAppDto(AdminServiceDto adminServiceDto,OAuthConsumerAppDTO appDTO) {

        AdminServiceDto oAuthConsumerAppDto = new AdminServiceDto();
        oAuthConsumerAppDto.setApplicationName(appDTO.getApplicationName());
        oAuthConsumerAppDto.setOauthVersion(appDTO.getOAuthVersion());
        oAuthConsumerAppDto.setCallbackUrl(appDTO.getCallbackUrl());
        oAuthConsumerAppDto.setGrantTypes(appDTO.getGrantTypes());
        oAuthConsumerAppDto.setOauthConsumerKey(appDTO.getOauthConsumerKey());

        if (adminServiceDto.getOauthConsumerSecret() != null) {
            oAuthConsumerAppDto.setOauthConsumerSecret(adminServiceDto.getOauthConsumerSecret());
        } else {
            oAuthConsumerAppDto.setOauthConsumerSecret(randomSecretKeyGenerator().toString());
        }
        oAuthConsumerAppDto.setPkceMandatory(adminServiceDto.isPkceMandatory());
        oAuthConsumerAppDto.setPkceSupportPlain(adminServiceDto.isPkceSupportPlain());

        return oAuthConsumerAppDto;
    }


    public AdminServiceDto transformToAdminServiceDto(OAuthConsumerAppDTO oAuthConsumerAppDTO) {

        adminServiceDto = new AdminServiceDto();
        adminServiceDto.setApplicationName(oAuthConsumerAppDTO.getApplicationName());
        adminServiceDto.setOauthVersion(oAuthConsumerAppDTO.getOAuthVersion());
        adminServiceDto.setCallbackUrl(oAuthConsumerAppDTO.getCallbackUrl());
        adminServiceDto.setGrantTypes(oAuthConsumerAppDTO.getGrantTypes());
        adminServiceDto.setOauthConsumerKey(oAuthConsumerAppDTO.getOauthConsumerKey());
        adminServiceDto.setOauthConsumerSecret(oAuthConsumerAppDTO.getOauthConsumerSecret());
        adminServiceDto.setPkceMandatory(oAuthConsumerAppDTO.getPkceMandatory());
        adminServiceDto.setPkceSupportPlain(oAuthConsumerAppDTO.getPkceSupportPlain());

        return adminServiceDto;

    }

    public Long randomSecretKeyGenerator() {
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        return number;
    }
}
