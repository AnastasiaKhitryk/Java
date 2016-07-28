package by.epam.task8.dao;

import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Discount;

import java.util.ArrayList;
import java.util.List;

public interface DiscountDao extends CommonDao<Discount>{
    int addNewDiscount(Discount discount) throws DaoException;
    int deleteDiscountById(int id) throws DaoException;
    List<Discount> extractAllDiscount() throws DaoException;
}

