package by.epam.task8.dao.mysql.impl;

import by.epam.task8.dao.DiscountDao;
import by.epam.task8.dao.exception.ConnectionPoolException;
import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.dao.mysql.connection.ConnectionPool;
import by.epam.task8.entity.Discount;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.*;


public class MysqlDiscountDao implements DiscountDao{
    private Logger logger = Logger.getLogger(String.valueOf(MysqlDiscountDao.class));

    private final static String ADD_DISCOUNT = "INSERT INTO discount(name,amount_of_discount,start_date_time,finish_date_time) VALUES (?,?,?,?)";
    private final static String DELETE_DISCOUNT_BY_ID = "DELETE FROM discount WHERE id = ? )";
    private final static String SELECT_ALL_DISCOUNT = "SELECT * FROM discount)";

    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String AMOUNT_OF_DISCOUNT = "amount_of_discount";
    private final static String START_DATE_TIME = "start_date_time";
    private final static String FINISH_DATE_TIME = "finish_date_time";

    @Override
    public int addNewDiscount(Discount discount) throws DaoException {
        int count;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(ADD_DISCOUNT);

            preparedStatement.setString(1, discount.getName());
            preparedStatement.setInt(2, discount.getAmount_of_discount());
            preparedStatement.setDate(3, discount.getStart_date_time());
            preparedStatement.setDate(4, discount.getFinish_date_time());

            count = preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("SQLException "+ e);
            throw new DaoException("SQLException" + e);
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPoolException "+ e);
            throw new DaoException("ConnectionPoolException" + e);
        } finally {
            try{
                preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            }catch (SQLException e){
                logger.error("SQLException "+ e);
                throw new DaoException("SQLException" + e);
            }catch (ConnectionPoolException e){
                logger.error("ConnectionPoolException "+ e);
                throw new DaoException("ConnectionPoolException" + e);
            }
        }
        return count;
    }

    @Override
    public int deleteDiscountById(int id) throws DaoException{
        int count = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_DISCOUNT_BY_ID);

            preparedStatement.setInt(1, id);
            count = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQLException "+ e);
            throw new DaoException("SQLException " + e);
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPoolException "+ e);
            throw new DaoException("ConnectionPoolException " + e);
        } finally {
            try{
                preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            }catch (SQLException e){
                logger.error("SQLException "+ e);
                throw new DaoException("SQLException" + e);
            }catch (ConnectionPoolException e){
                logger.error("ConnectionPoolException "+ e);
                throw new DaoException("ConnectionPoolException" + e);
            }
        }
        return count;
    }

    @Override
    public ArrayList<Discount> extractAllDiscount() throws DaoException{
        ArrayList<Discount> discountList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_DISCOUNT);

            while (resultSet.next()){
                Discount discount = new Discount();

                discount.setId(resultSet.getInt(ID));
                discount.setName(resultSet.getString(NAME));
                discount.setAmount_of_discount(resultSet.getInt(AMOUNT_OF_DISCOUNT));
                discount.setStart_date_time(resultSet.getDate(START_DATE_TIME));
                discount.setFinish_date_time(resultSet.getDate(FINISH_DATE_TIME));

                discountList.add(discount);
            }
        } catch (SQLException e) {
            logger.error("SQLException "+ e);
            throw new DaoException("SQLException " + e);
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPoolException "+ e);
            throw new DaoException("ConnectionPoolException " + e);
        } finally {
            try{
                statement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            }catch (SQLException e){
                logger.error("SQLException "+ e);
                throw new DaoException("SQLException" + e);
            }catch (ConnectionPoolException e){
                logger.error("ConnectionPoolException "+ e);
                throw new DaoException("ConnectionPoolException" + e);
            }
        }

        return discountList;
    }


    @Override
    public ArrayList<Discount> getById(int id) throws DaoException {
        //TODO
        return null;

    }
}
