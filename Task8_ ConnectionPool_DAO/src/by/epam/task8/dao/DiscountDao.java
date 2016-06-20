package by.epam.task8.dao;

import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Discount;

import java.util.ArrayList;

public interface DiscountDao {
    int addNewDiscount(Discount discount) throws DaoException;
    int deleteDiscountById(int id) throws DaoException;
    ArrayList<Discount> extractAllDiscount() throws DaoException;
}
