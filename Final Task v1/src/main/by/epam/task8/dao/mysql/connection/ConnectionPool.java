package by.epam.task8.dao.mysql.connection;

import by.epam.task8.dao.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {

    private static Logger logger = Logger.getLogger(ConnectionPool.class);
    private static Lock lock = new ReentrantLock();
    private static ConnectionPool instance = null;

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.db");

    private final String DB_DRIVER = resourceBundle.getString("DB_DRIVER");
    private final String DB_URL = resourceBundle.getString("DB_URL");
    private final String DB_USERNAME = resourceBundle.getString("DB_USERNAME");
    private final String DB_PASSWORD = resourceBundle.getString("DB_PASSWORD");

    private final Integer DB_POOL_SIZE = Integer.valueOf(resourceBundle.getString("DB_POOL_SIZE"));

    private LinkedBlockingDeque<Connection> unusedConnectionList = new LinkedBlockingDeque<>();
    private LinkedBlockingDeque<Connection> usedConnectionList = new LinkedBlockingDeque<>();

    private Semaphore semaphore = new Semaphore(DB_POOL_SIZE);

    private ConnectionPool() throws ConnectionPoolException {
        try {
            Class.forName(DB_DRIVER);

            for (int i = 0; i < DB_POOL_SIZE; i++) {
                unusedConnectionList.add(openConnection());
            }
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException " + e);
            throw new ConnectionPoolException("ClassNotFoundException " + e);
        }
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {
        if (instance != null) {
            return instance;
        } else {
            //synchronized (instance){}
            lock.lock();
            instance = new ConnectionPool();
            lock.unlock();
            return instance;
        }
    }

    private Connection openConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            logger.error("SQLException " + e);
            throw new ConnectionPoolException("SQLException" + e);
        }
        return connection;
    }

    public Connection getConnection() throws ConnectionPoolException {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            logger.error("InterruptedException " + e);
            throw new ConnectionPoolException("InterruptedException" + e);
        }
        Connection connection = unusedConnectionList.pollLast();
        usedConnectionList.addFirst(connection);

        return connection;
    }

    public void releaseConnection(Connection connection) {
        unusedConnectionList.addFirst(connection);
        usedConnectionList.remove(connection);
        semaphore.release();
    }

    public void closeAll() throws SQLException {

        Iterator<Connection> unusedIterator = unusedConnectionList.iterator();
        while (unusedIterator.hasNext()) {
            unusedIterator.next().close();
        }

        Iterator<Connection> usedIterator = usedConnectionList.iterator();
        while (usedIterator.hasNext()) {
            usedIterator.next().close();
        }

        unusedConnectionList.clear();
        usedConnectionList.clear();
    }

}
