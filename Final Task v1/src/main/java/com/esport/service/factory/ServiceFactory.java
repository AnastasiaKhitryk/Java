package main.java.com.esport.service.factory;

import main.java.com.esport.service.PoolService;
import main.java.com.esport.service.UserService;
import main.java.com.esport.service.impl.PoolServiceImpl;
import main.java.com.esport.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private UserService userService = new UserServiceImpl();
	private PoolService poolService = new PoolServiceImpl();
	
	private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance;
    }

	public UserService getUserService() {
		return userService;
	}
	
	public PoolService getPoolService() {
		return poolService;
	}
   
}
