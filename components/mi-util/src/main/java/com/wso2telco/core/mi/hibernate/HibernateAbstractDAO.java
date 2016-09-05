package com.wso2telco.core.mi.hibernate;

import org.hibernate.Session;


public abstract class HibernateAbstractDAO {
	

	protected Session getSession(){
		return SessionFactoryUtil.getInstance().getCurrentSession();
	}
}
