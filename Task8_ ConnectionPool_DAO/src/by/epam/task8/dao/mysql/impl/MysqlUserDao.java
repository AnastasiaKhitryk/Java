package by.epam.task8.dao.mysql.impl;

import by.epam.task8.dao.UserDao;
import by.epam.task8.dao.exception.ConnectionPoolException;
import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.dao.mysql.connection.ConnectionPool;
import by.epam.task8.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MysqlUserDao implements UserDao {
    private Logger logger = Logger.getLogger(String.valueOf(MysqlOrderDao.class));

    private final static String ADD_USER = "INSERT INTO user (email, password, role) VALUES (?,?,?)";
    private final static String GET_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?";

    private final static String ID = "id";
    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";
    private final static String ROLE = "role";

    @Override
    public int addUser(User user) throws DaoException {
        int count;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(ADD_USER);

            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getRole());

            count = preparedStatement.executeUpdate();
        }catch (SQLException e){
            //logger.error(e.getMessage());
            throw new DaoException("SQLException" + e);
        } catch (ConnectionPoolException e) {
            //logger.error(e.getMessage());
            throw new DaoException("ConnectionPoolException " + e);
        } finally {
            try{
                preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            }catch (SQLException e){
                //logger.error(e.getMessage());
                throw new DaoException("SQLException" + e);
            }catch (ConnectionPoolException e){
                //logger.error(e.getMessage());
                throw new DaoException("ConnectionPoolException" + e);
            }
        }
        return count;
    }

    @Override
    public ArrayList<User> extractUserById(int id) throws DaoException {
        ArrayList<User> userList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_USER_BY_ID);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                User user = new User();

                user.setId(resultSet.getInt(ID));
                user.setEmail(resultSet.getString(EMAIL));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setRole(resultSet.getString(ROLE));

                userList.add(user);
            }

        } catch (SQLException e) {
            //logger.error(e.getMessage());
            throw new DaoException("SQLException " + e);
        } catch (ConnectionPoolException e) {
            //logger.error(e.getMessage());
            throw new DaoException("ConnectionPoolException " + e);
        } finally {
            try{
                preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            }catch (SQLException e){
                //logger.error(e.getMessage());
                throw new DaoException("SQLException" + e);
            }catch (ConnectionPoolException e){
                //logger.error(e.getMessage());
                throw new DaoException("ConnectionPoolException" + e);
            }
        }

        return userList;
    }

}
