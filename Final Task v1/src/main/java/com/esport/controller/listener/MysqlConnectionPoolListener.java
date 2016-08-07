package main.java.com.esport.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import main.java.com.esport.service.PoolService;
import main.java.com.esport.service.exception.ServiceException;
import main.java.com.esport.service.factory.ServiceFactory;

public class MysqlConnectionPoolListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        PoolService poolService = serviceFactory.getPoolService();
        try {
            poolService.init();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        PoolService poolService = serviceFactory.getPoolService();
        try {
            poolService.destroy();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
	}
}
