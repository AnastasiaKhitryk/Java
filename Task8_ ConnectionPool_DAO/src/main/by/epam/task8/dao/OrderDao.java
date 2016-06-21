package by.epam.task8.dao;

import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Order;

import java.util.ArrayList;

public interface OrderDao extends CommonDao<Order>{
    int addOrder(Order order) throws DaoException;
    ArrayList<Order> getAllOrder() throws DaoException;
}
