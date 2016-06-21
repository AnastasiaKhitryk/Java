package by.epam.task8.dao;

import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Product;

public interface ProductDao extends CommonDao<Product>{
    int addProduct(Product product) throws DaoException;
}
