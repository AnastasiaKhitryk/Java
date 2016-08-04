package main.java.com.esport.dao.factory;

import main.java.com.esport.dao.exception.DaoException;
import main.java.com.esport.dao.interfaces.UserDao;

public abstract class DaoFactory {
	private static final int MY_SQL = 1;

	private static final DaoFactory mysqlDaoFactory = new MysqlDaoFactory();

	public abstract UserDao getUserDao();

	public static DaoFactory getInstance() throws DaoException {
		int factoryType = readConfig();

		switch (factoryType) {
		case MY_SQL:
			return mysqlDaoFactory;
		default:
			throw new DaoException("Wrong config file for DAO layer");
		}
	}

	private static int readConfig() {
		return MY_SQL;
	}
}
