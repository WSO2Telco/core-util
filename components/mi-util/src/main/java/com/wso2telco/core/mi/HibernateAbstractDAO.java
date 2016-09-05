package com.wso2telco.core.mi;

import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;

public abstract class HibernateAbstractDAO<E> extends AbstractDAO<E> {

	public HibernateAbstractDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

}
