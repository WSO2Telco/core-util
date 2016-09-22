package com.wso2telco.core.mi.hibernate;

import org.apache.commons.logging.Log;
import org.hibernate.Session;


public abstract class HibernateAbstractDAO {
	
	protected Log LOG;

	public void saveOrUpdate(Object object) throws Exception {
		try {
			Session session = getSession();
			session.saveOrUpdate(object);
		} catch (Exception ex) {
			LOG.debug("Error Occured While Saving Object. ", ex);
			throw ex;
		}

	}

	protected Session getSession(){
		return SessionFactoryUtil.getSession();
	}
}
