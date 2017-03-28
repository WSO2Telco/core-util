package com.wso2telco.core.mi.hibernate;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

public class HibernateSessionCleanner implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        SessionFactoryUtil.closeSession();

    }

	/*@Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
		

	}
*/
}
