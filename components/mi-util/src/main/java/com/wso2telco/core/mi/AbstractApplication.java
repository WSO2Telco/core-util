package com.wso2telco.core.mi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wso2telco.core.mi.hibernate.HibernateSessionCleanner;

import io.dropwizard.Application;
import io.dropwizard.jetty.ConnectorFactory;
import io.dropwizard.jetty.HttpConnectorFactory;
import io.dropwizard.jetty.HttpsConnectorFactory;
import io.dropwizard.server.DefaultServerFactory;
import io.dropwizard.server.ServerFactory;
import io.dropwizard.server.SimpleServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerDropwizard;
 
public abstract class AbstractApplication< E extends ConfigDTO>  extends Application<E> {
//	static Logger log = LoggerFactory.getLogger(Appinitializer.class);

	private final SwaggerDropwizard swaggerDropwizard = new SwaggerDropwizard();

	@Override
	public void initialize(Bootstrap<E> bootstrap) {
		swaggerDropwizard.onInitialize(bootstrap);
	}
	
	protected abstract List<Object> getRestFulComponents();
	protected abstract void runInit(E e,Environment env);
	@Override
	public void run(E arg0, Environment env) throws Exception {
		System.out.println("Server start with configuration :" + arg0);
		/**
		 * initialize configuration reading
		 */
		ConfigReader.init(arg0, env);
		/**
		 * initialize token pool service
		 */
		runInit(arg0,env);

		for (Object iterable_element : getRestFulComponents()) {
			env.jersey().register(iterable_element);
		}
		
		env.jersey().register( new HibernateSessionCleanner());
		

		HttpConnectorFactory connector = getHttpConnectionFactory(arg0);

		swaggerDropwizard.onRun(arg0, env, connector.getBindHost(), connector.getPort());

	}


	private HttpConnectorFactory getHttpConnectionFactory(ConfigDTO configuration) {
		List<ConnectorFactory> connectorFactories = getConnectorFactories(configuration);
		for (ConnectorFactory connectorFactory : connectorFactories) {
			if (connectorFactory instanceof HttpsConnectorFactory) {
				return (HttpConnectorFactory) connectorFactory; // if we find 
																// https skip
																// the others
			}
		}
		for (ConnectorFactory connectorFactory : connectorFactories) {
			if (connectorFactory instanceof HttpConnectorFactory) {
				return (HttpConnectorFactory) connectorFactory; // if not https
																// pick http
			}
		}

		throw new IllegalStateException("Unable to find an HttpServerFactory");
	}

	private List<ConnectorFactory> getConnectorFactories(ConfigDTO configuration) {
		ServerFactory serverFactory = configuration.getServerFactory();
		if (serverFactory instanceof SimpleServerFactory) {
			return Collections.singletonList(((SimpleServerFactory) serverFactory).getConnector());
		} else if (serverFactory instanceof DefaultServerFactory) {
			return new ArrayList<>(((DefaultServerFactory) serverFactory).getApplicationConnectors());
		} else {
			throw new IllegalStateException("Unknown ServerFactory implementation: " + serverFactory.getClass());
		}
	}
	

}
