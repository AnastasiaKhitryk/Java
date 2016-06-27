package by.epam.task8.dao.mysql.impl;

import by.epam.task8.dao.UserDao;
import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MysqlUserDao extends  MysqlCommonActions implements UserDao {
    private Logger logger = Logger.getLogger(String.valueOf(MysqlUserDao.class));

    private final static String ADD_USER = "INSERT INTO user (email, password, role) VALUES (?,?,?)";
    private final static String GET_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?";
    private final static String GET_ALL_USER = "SELECT * FROM";

    private final static String ID = "id";
    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";
    private final static String ROLE = "role";

    @Override
    public int addUser(User user) throws DaoException {
        int count;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = getPreparedStatement(connection, ADD_USER);

        try{
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getRole());

            count = preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("SQLException" + e);
            throw new DaoException("SQLException" + e);
        } finally {
            releaseData(preparedStatement, connection);
        }
        return count;
    }

    @Override
    public List<User> getById(int id) throws DaoException {
        List<User> userList = null;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = getPreparedStatement(connection, GET_USER_BY_ID);

        try{
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            userList = getUserList(resultSet);

        } catch (SQLException e) {
            logger.error("SQLException" + e);
            throw new DaoException("SQLException " + e);
        } finally {
            releaseData(preparedStatement, connection);
        }

        return userList;
    }

    @Override
    public List<User> getAllUser() throws DaoException {
        List<User> userList = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = getStatement(connection);

        try{
            ResultSet resultSet = statement.executeQuery(GET_ALL_USER);
            userList = getUserList(resultSet);

        } catch (SQLException e) {
            logger.error("SQLException" + e);
            throw new DaoException("SQLException " + e);
        } finally {
            releaseData(statement, connection);
        }

        return userList;
    }

    private List<User> getUserList(ResultSet resultSet) throws SQLException {
        ArrayList<User> userList = new ArrayList<>();
        while (resultSet.next()){
            User user = new User();

            user.setId(resultSet.getInt(ID));
            user.setEmail(resultSet.getString(EMAIL));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setRole(resultSet.getString(ROLE));

            userList.add(user);
        }

        return userList;
    }

}
