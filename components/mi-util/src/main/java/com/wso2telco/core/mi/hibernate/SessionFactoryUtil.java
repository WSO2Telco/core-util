package com.wso2telco.core.mi.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.wso2telco.core.mi.ConfigReader;

public class SessionFactoryUtil {

  /** The single instance of hibernate SessionFactory */
  private static org.hibernate.SessionFactory sessionFactory;

	/**
	 * disable contructor to guaranty a single instance
	 */
	private SessionFactoryUtil() {
		if(sessionFactory==null){
			sessionFactory= initSessionFactory();
		} 
	}

	private SessionFactory initSessionFactory(){
		Configuration config = new Configuration().configure( );
		ConfigReader configReader = ConfigReader.getInstance();
		config.setProperty("hibernate.connection.url",configReader.getConfigDTO() .getDataSourceFactory().getUrl());
		config.setProperty("hibernate.connection.username",configReader.getConfigDTO().getDataSourceFactory().getUser());
		config.setProperty("hibernate.connection.password", configReader.getConfigDTO().getDataSourceFactory().getPassword());
		
		
		return config.buildSessionFactory();
	}

	public static  SessionFactoryUtil getInstance() {
		 
		return new SessionFactoryUtil();
		 
	}
  /**
   * Opens a session and will not bind it to a session context
   * @return the session
   */
	public Session openSession() {
		return sessionFactory.openSession();
	}

	/**
   * Returns a session from the session context. If there is no session in the context it opens a session,
   * stores it in the context and returns it.
	 * This factory is intended to be used with a hibernate.cfg.xml
	 * 
	 * @return the session
	 */
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

  /**
   * closes the session factory
   */
	public static void close(){
		if (sessionFactory != null)
			sessionFactory.close();
		sessionFactory = null;
	
	}
}
