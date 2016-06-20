package by.epam.task8.dao.mysql.connection;

import by.epam.task8.dao.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.LinkedBlockingDeque;

//import org.apache.log4j.Logger;

public class ConnectionPool {

    private static ConnectionPool instance = null;

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.db");
    private Properties properties = new Properties();

    private final String DB_DRIVER = resourceBundle.getString("DB_DRIVER");
    private final String DB_URL = resourceBundle.getString("DB_URL");
    private final String DB_USERNAME = resourceBundle.getString("DB_USERNAME");
    private final String DB_PASSWORD = resourceBundle.getString("DB_PASSWORD");
    private final Integer DB_POOL_SIZE = Integer.valueOf(resourceBundle.getString("DB_POOL_SIZE"));

    private LinkedBlockingDeque<Connection> unusedConnectionList = new LinkedBlockingDeque<Connection>();
    private LinkedBlockingDeque<Connection> usedConnectionList = new LinkedBlockingDeque<Connection>();

   // private static Logger logger = Logger.getLogger(ConnectionPool.class);

    public static ConnectionPool getInstance() throws ConnectionPoolException {
        if (instance != null) {
            return instance;
        } else {
            return new ConnectionPool();
        }
    }

    private ConnectionPool() throws ConnectionPoolException{
        try{
            Class.forName(DB_DRIVER);

            for (int i = 0; i < DB_POOL_SIZE; i++) {
                unusedConnectionList.add(openConnection());
            }
        }catch (ClassNotFoundException e){
            new ConnectionPoolException("ClassNotFoundException " + e);
        }


    }

    private Connection openConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME,DB_PASSWORD);
        } catch (SQLException e) {
            throw new ConnectionPoolException("SQLException" + e);
        }
        return connection;
    }

    public Connection getConnection(){
        Connection connection = null;
        connection = unusedConnectionList.pollLast();
        usedConnectionList.addFirst(connection);

        return connection;
    }

    public void releaseConnection(Connection connection) {
        unusedConnectionList.addFirst(connection);
        usedConnectionList.remove(connection);
    }

    public void closeAll() throws SQLException {

        while (unusedConnectionList.iterator().hasNext()){
            unusedConnectionList.iterator().next().close();
        }

        while (usedConnectionList.iterator().hasNext()){
            unusedConnectionList.iterator().next().close();
        }

        unusedConnectionList.clear();
        usedConnectionList.clear();
    }

}
