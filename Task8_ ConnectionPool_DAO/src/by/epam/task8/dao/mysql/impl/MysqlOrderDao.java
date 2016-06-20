package by.epam.task8.dao.mysql.impl;


import by.epam.task8.dao.OrderDao;
import by.epam.task8.dao.exception.ConnectionPoolException;
import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.dao.mysql.connection.ConnectionPool;
import by.epam.task8.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MysqlOrderDao implements OrderDao{
    private Logger logger = Logger.getLogger(String.valueOf(MysqlOrderDao.class));

    private final static String INSERT_ORDER = "INSERT INTO order (user_id, total_price, status, delivery_type, address, phone, payment_type, date, order_detail) VALUES (?,?,?,?,?,?,?,?,?)";
    private final static String SELECT_ALL_ORDERS = "SELECT * FROM order";
    private final static String GET_ALL_ORDER_BY_USER_ID = "SELECT * FROM order WHERE user_id = ?";

    private final static String ID = "id";
    private final static String USER_ID = "user_id";
    private final static String TOTAL_PRICE = "total_price";
    private final static String STATUS = "status";
    private final static String DELIVERY_TYPE = "delivery_type";
    private final static String ADDRESS = "address";
    private final static String PHONE = "phone";
    private final static String PAYMENT_TYPE = "payment_type";
    private final static String DATE = "date";
    private final static String ORDER_DETAIL = "order_detail";


    public int addOrder(Order order) throws DaoException {
        int count;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_ORDER);

            preparedStatement.setInt(1,order.getUser_id());
            preparedStatement.setBigDecimal(2,order.getTotal_price());
            preparedStatement.setString(3,order.getStatus());
            preparedStatement.setString(4,order.getDelivery_type());
            preparedStatement.setString(5,order.getAddress());
            preparedStatement.setString(6,order.getAddress());
            preparedStatement.setString(7,order.getPhone());
            preparedStatement.setString(8,order.getPayment_type());
            preparedStatement.setDate(9,order.getDate());
            preparedStatement.setString(10,order.getOrder_detail());

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

    public ArrayList<Order> extractAllOrder() throws DaoException {
        ArrayList<Order> orderList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_ORDERS);

            while (resultSet.next()){
                Order order = new Order();

                order.setId(resultSet.getInt(ID));
                order.setUser_id(resultSet.getInt(USER_ID));
                order.setTotal_price(resultSet.getBigDecimal(TOTAL_PRICE));
                order.setStatus(resultSet.getString(STATUS));
                order.setDelivery_type(resultSet.getString(DELIVERY_TYPE));
                order.setAddress(resultSet.getString(ADDRESS));
                order.setPhone(resultSet.getString(PHONE));
                order.setPayment_type(resultSet.getString(PAYMENT_TYPE));
                order.setDate(resultSet.getDate(DATE));
                order.setOrder_detail(resultSet.getString(ORDER_DETAIL));

                orderList.add(order);
            }
        } catch (SQLException e) {
            //logger.error(e.getMessage());
            throw new DaoException("SQLException " + e);
        } catch (ConnectionPoolException e) {
            //logger.error(e.getMessage());
            throw new DaoException("ConnectionPoolException " + e);
        } finally {
            try{
                statement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            }catch (SQLException e){
                //logger.error(e.getMessage());
                throw new DaoException("SQLException" + e);
            }catch (ConnectionPoolException e){
                //logger.error(e.getMessage());
                throw new DaoException("ConnectionPoolException" + e);
            }
        }

        return orderList;
    }

    public ArrayList<Order> extractOrderByUserId(int userId) throws DaoException {
        ArrayList<Order> orderList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_ORDER_BY_USER_ID);

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Order order = new Order();

                order.setId(resultSet.getInt(ID));
                order.setUser_id(resultSet.getInt(USER_ID));
                order.setTotal_price(resultSet.getBigDecimal(TOTAL_PRICE));
                order.setStatus(resultSet.getString(STATUS));
                order.setDelivery_type(resultSet.getString(DELIVERY_TYPE));
                order.setAddress(resultSet.getString(ADDRESS));
                order.setPhone(resultSet.getString(PHONE));
                order.setPayment_type(resultSet.getString(PAYMENT_TYPE));
                order.setDate(resultSet.getDate(DATE));
                order.setOrder_detail(resultSet.getString(ORDER_DETAIL));

                orderList.add(order);
            }

        } catch (SQLException e) {
            //logger.error(e.getMessage());
            throw new DaoException("SQLException " + e);
        } catch (ConnectionPoolException e) {
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

        return orderList;
    }

}
