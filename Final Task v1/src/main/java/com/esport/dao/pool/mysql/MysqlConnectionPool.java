package main.java.com.esport.dao.pool.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MysqlConnectionPool {
	
	private static MysqlConnectionPool instance = new MysqlConnectionPool();

    private static final String DB_POOL_SIZE = "DB_POOL_SIZE";
    private static final String DB_URL = "DB_URL";
    private static final String DB_USERNAME = "DB_USERNAME";
    private static final String DB_PASSWORD = "DB_PASSWORD";
    private static final String DB_DRIVER = "DB_DRIVER";
    private static final String RESOURCE_BUNDLE_NAME = "resource/db";
    
    private String databaseUrl;
    private String userLogin;
    private String userPassword;

    private volatile boolean isAvailable = false;
    private volatile boolean isInit = false;
    
	private MysqlConnectionPool(){}
	
    private List<Connection> availableConnections = new ArrayList<>();
    private List<Connection> usedConnections = new ArrayList<>();
    
    private final Lock lock = new ReentrantLock();
    private final Condition atLeastOneFreeConnection = lock.newCondition();
	
    public static MysqlConnectionPool getInstance() {
        return instance;
    }
    
    public void init() throws MysqlConnectionPoolException {
        if(!isInit) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME);
            String numberOfConnectionsStr = resourceBundle.getString(DB_POOL_SIZE);
            String driverClassName = resourceBundle.getString(DB_DRIVER);
            String databaseUrl = resourceBundle.getString(DB_URL);
            String userLogin = resourceBundle.getString(DB_USERNAME);
            String userPassword = resourceBundle.getString(DB_PASSWORD);

            this.databaseUrl = databaseUrl;
            this.userLogin = userLogin;
            this.userPassword = userPassword;

            try {
                Class.forName(driverClassName).newInstance();
                for (int i = 0; i < Integer.parseInt(numberOfConnectionsStr); i++) {
                    Connection newConnection = DriverManager.getConnection(databaseUrl, userLogin, userPassword);

                    lock.lock();
                    availableConnections.add(newConnection);
                    isAvailable = true;
                    lock.unlock();
                }
                isInit = true;
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
                throw new MysqlConnectionPoolException("Cannot init a pool", e);
            }
        }
        else {
            throw new MysqlConnectionPoolException("Try to init already init pool");
        }
    }
    
    public void destroy() throws MysqlConnectionPoolException {
        if(isInit) {
            lock.lock();
            try {
                for (Connection connection : availableConnections) {
                    connection.close();
                }
                availableConnections.clear();
                for (Connection connection : usedConnections) {
                    connection.close();
                }
                usedConnections.clear();
                isAvailable = false;
                isInit = false;
            } catch (SQLException e) {
                throw new MysqlConnectionPoolException("Cannot destroy a pool", e);
            } finally {
                lock.unlock();
            }
        }
        else {
            throw new MysqlConnectionPoolException("Try to destroy not init pool");
        }
    }
    
    public Connection getConnection() throws InterruptedException, MysqlConnectionPoolException {
        if(isAvailable){
            lock.lock();
            try {
                while (availableConnections.isEmpty()){
                    atLeastOneFreeConnection.await();
                }

                Connection connection = availableConnections.remove(0);
                usedConnections.add(connection);
                return connection;
            } finally {
                lock.unlock();
            }
        }
        else {
            throw new MysqlConnectionPoolException("Try to use pool when it is not available");
        }
    }

    public void freeConnection(Connection connection) throws SQLException, MysqlConnectionPoolException {
        if(isAvailable){
            lock.lock();
            try {
                if (usedConnections.isEmpty() || !usedConnections.contains(connection)) {
                    throw new MysqlConnectionPoolException("Try to free pool which was created not in Connection Pool");
                }
                usedConnections.remove(connection);

                if (connection.isClosed()) {
                    connection = DriverManager.getConnection(databaseUrl, userLogin, userPassword);

                } else {
                    if (!connection.getAutoCommit()) {
                        connection.setAutoCommit(true);
                    }
                    if (connection.isReadOnly()) {
                        connection.setReadOnly(false);
                    }
                }

                availableConnections.add(connection);

                atLeastOneFreeConnection.signal();
            } finally {
                lock.unlock();
            }
        }
        else {
            throw new MysqlConnectionPoolException("Try to use pool when it is not available");
        }
    }
}
