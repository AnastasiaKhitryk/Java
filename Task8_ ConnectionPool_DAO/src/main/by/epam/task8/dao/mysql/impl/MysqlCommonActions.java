package by.epam.task8.dao.mysql.impl;

import by.epam.task8.dao.exception.ConnectionPoolException;
import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.dao.mysql.connection.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

class MysqlCommonActions {
    private Logger logger = Logger.getLogger(String.valueOf(MysqlCommonActions.class));

    Connection getConnection() throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPoolException " + e);
            throw new DaoException("ConnectionPoolException" + e);
        }
        return connection;
    }

    Statement getStatement(Connection connection) throws DaoException {
        Statement statement;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            logger.error("SQLException " + e);
            throw new DaoException("SQLException" + e);
        }
        return statement;
    }

    PreparedStatement getPreparedStatement(Connection connection, String sqlStatement) throws DaoException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sqlStatement);
        } catch (SQLException e) {
            logger.error("SQLException " + e);
            throw new DaoException("SQLException" + e);
        }
        return preparedStatement;
    }

    void releaseConnection(Connection connection) throws DaoException {
        try {
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPoolException " + e);
            throw new DaoException("ConnectionPoolException" + e);
        }
    }

    void closeStatement(Statement statement) throws DaoException {
        try {
            statement.close();
        } catch (SQLException e) {
            logger.error("SQLException " + e);
            throw new DaoException("SQLException" + e);
        }
    }

    void releaseData(Statement statement, Connection connection) throws DaoException {
        closeStatement(statement);
        releaseConnection(connection);
    }
}
