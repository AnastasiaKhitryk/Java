package by.epam.task8.dao;

import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Supplier;
import by.epam.task8.entity.User;

import java.util.ArrayList;
import java.util.List;

public interface SupplierDao extends CommonDao<Supplier>{
    List<Supplier> getAllOrder() throws DaoException;
}
