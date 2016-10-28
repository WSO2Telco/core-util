package com.wso2telco.core.mi.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.hibernate.Session;
import org.hibernate.Transaction;


public abstract class HibernateAbstractDAO {
	
	protected Log LOG;

	public void saveOrUpdate(Object object) throws Exception {		
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(object);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			LOG.debug("Error Occured While Saving Object. ", ex);
			throw ex;
		}
	}
	
	public void saveOrUpdateList(List<Object> objectList) throws Exception {		
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
		    for(Object eachListObj : objectList){
			session.saveOrUpdate(eachListObj);
		    }
		    transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			LOG.debug("Error Occured While Saving List of Object. ", ex);
			throw ex;
		}
	}

	protected Session getSession(){
		return SessionFactoryUtil.getSession();
	}
}
