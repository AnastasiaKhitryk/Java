package by.epam.task8.dao;

import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Supplier;

import java.util.ArrayList;

public interface SupplierDao {
    ArrayList<Supplier> extractAllOrder() throws DaoException;
}
