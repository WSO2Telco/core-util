package com.wso2telco.core.userprofile.util;

import org.wso2.carbon.um.ws.api.stub.ClaimDTO;

public class ClaimUtil {

	public static org.wso2.carbon.user.core.claim.Claim[] convertToClaims(ClaimDTO[] claims) {

		if (claims == null) {

			return new org.wso2.carbon.user.core.claim.Claim[0];
		}

		org.wso2.carbon.user.core.claim.Claim[] claimz = new org.wso2.carbon.user.core.claim.Claim[claims.length];
		int i = 0;
		for (ClaimDTO claim : claims) {

			claimz[i] = convertToClaim(claim);
			i++;
		}

		return claimz;
	}

	public static org.wso2.carbon.user.core.claim.Claim convertToClaim(ClaimDTO claim) {

		if (claim == null) {

			return null;
		}

		return convertToClaim(claim, null);
	}

	private static org.wso2.carbon.user.core.claim.Claim convertToClaim(ClaimDTO claimDTO,
			org.wso2.carbon.um.ws.api.stub.Claim claim) {

		if (claim == null) {

			org.wso2.carbon.user.core.claim.Claim claimz = new org.wso2.carbon.user.core.claim.Claim();
			claimz.setClaimUri(claimDTO.getClaimUri());
			claimz.setDescription(claimDTO.getDescription());
			claimz.setDialectURI(claimDTO.getDialectURI());
			claimz.setDisplayOrder(claimDTO.getDisplayOrder());
			claimz.setDisplayTag(claimDTO.getDisplayTag());
			claimz.setRegEx(claimDTO.getRegEx());
			claimz.setRequired(claimDTO.getRequired());
			claimz.setSupportedByDefault(claimDTO.getSupportedByDefault());
			claimz.setValue(claimDTO.getValue());
			return claimz;
		}

		org.wso2.carbon.user.core.claim.Claim claimz = new org.wso2.carbon.user.core.claim.Claim();
		claimz.setClaimUri(claim.getClaimUri());
		claimz.setDescription(claim.getDescription());
		claimz.setDialectURI(claim.getDialectURI());
		claimz.setDisplayOrder(claim.getDisplayOrder());
		claimz.setDisplayTag(claim.getDisplayTag());
		claimz.setRegEx(claim.getRegEx());
		claimz.setRequired(claim.getRequired());
		claimz.setSupportedByDefault(claim.getSupportedByDefault());
		claimz.setValue(claim.getValue());
		return claimz;
	}

	public static org.wso2.carbon.user.core.claim.Claim convertToClaim(org.wso2.carbon.um.ws.api.stub.Claim claim) {

		if (claim == null) {

			return null;
		}

		return convertToClaim(null, claim);
	}
}
