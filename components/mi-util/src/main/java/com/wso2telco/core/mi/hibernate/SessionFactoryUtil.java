package com.wso2telco.core.mi.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.wso2telco.core.mi.ConfigReader;

public class SessionFactoryUtil {

    /**
     * The single instance of hibernate SessionFactory
     */
    private static SessionFactory sessionFactory;
    private static final ThreadLocal<Session> threadLocal;
    private static ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

			/*
             * serviceRegistry = new
			 * StandardServiceRegistryBuilder().applySettings(configuration.
			 * getProperties()).build(); sessionFactory =
			 * configuration.buildSessionFactory(serviceRegistry);
			 */
            sessionFactory = initSessionFactory();
            threadLocal = new ThreadLocal<Session>();

        } catch (Throwable t) {
            t.printStackTrace();
            throw new ExceptionInInitializerError(t);
        }
    }

    /**
     * disable contructor to guaranty a single instance
     */
    private SessionFactoryUtil() {
        if (sessionFactory == null) {
            sessionFactory = initSessionFactory();
        }
    }

    private static SessionFactory initSessionFactory() {
        Configuration config = new Configuration().configure();
        ConfigReader configReader = ConfigReader.getInstance();
        config.setProperty("hibernate.connection.url", configReader.getConfigDTO().getDataSourceFactory().getUrl());
        config.setProperty("hibernate.connection.username",
                configReader.getConfigDTO().getDataSourceFactory().getUser());
        config.setProperty("hibernate.connection.password",
                configReader.getConfigDTO().getDataSourceFactory().getPassword());

        return config.buildSessionFactory();
    }

    public static Session getSession() {
        Session session = threadLocal.get();
        if (session == null || (session != null && !session.isOpen())) {
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;
    }

    /**
     * close the session if open
     */
    public static void closeSession() {
        Session session = threadLocal.get();
        if (session != null) {
            session.close();
            threadLocal.set(null);
        }
    }

    /**
     * closes the session factory
     */
    public static void close() {
        if (sessionFactory != null)
            sessionFactory.close();
        sessionFactory = null;

    }
}
