package main.java.com.esport.dao.factory;

import main.java.com.esport.dao.impl.mysql.MysqlUserDao;
import main.java.com.esport.dao.interfaces.UserDao;

public class MysqlDaoFactory extends DaoFactory{

	 private final UserDao mysqlUserDao = new MysqlUserDao();
	
	@Override
	public UserDao getUserDao() {
		return mysqlUserDao;
	}

}
