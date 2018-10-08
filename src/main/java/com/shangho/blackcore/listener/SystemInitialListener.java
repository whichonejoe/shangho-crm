package com.shangho.blackcore.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.configuration.conf.Env;

public class SystemInitialListener implements ServletContextListener {
	private final static Logger logger = LoggerFactory.getLogger(SystemInitialListener.class);

	public SystemInitialListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			JAXPConfigurator.configure(Env.SYSTEM_DEF_PATH + Env.PROXOOL_PATH, false);
		} catch (ProxoolException e) {
			logger.error(e.getMessage(), e);
			System.exit(0);
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
