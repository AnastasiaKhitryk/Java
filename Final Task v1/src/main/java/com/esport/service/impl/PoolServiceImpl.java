package main.java.com.esport.service.impl;

import main.java.com.esport.dao.pool.mysql.MysqlConnectionPool;
import main.java.com.esport.dao.pool.mysql.MysqlConnectionPoolException;
import main.java.com.esport.service.PoolService;
import main.java.com.esport.service.exception.ServiceException;

public class PoolServiceImpl implements PoolService{

	@Override
	public void init() throws ServiceException {
		 MysqlConnectionPool mySQLConnectionPool = MysqlConnectionPool.getInstance();
	        try {
	            mySQLConnectionPool.init();
	        } catch (MysqlConnectionPoolException e) {
	            throw new ServiceException("Cannot init a pool", e);
	        }
	}

	@Override
	public void destroy() throws ServiceException {
		 MysqlConnectionPool mySQLConnectionPool = MysqlConnectionPool.getInstance();
	        try {
	            mySQLConnectionPool.destroy();
	        } catch (MysqlConnectionPoolException e) {
	            throw new ServiceException("Cannot destroy a pool", e);
	        }
	}

}
