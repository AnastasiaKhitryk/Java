package by.epam.task8.dao;

import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Order;

import java.util.ArrayList;

public interface OrderDao {
    int addOrder(Order order) throws DaoException;
    ArrayList<Order> extractAllOrder() throws DaoException;
    ArrayList<Order> extractOrderByUserId(int userId) throws DaoException;
}
