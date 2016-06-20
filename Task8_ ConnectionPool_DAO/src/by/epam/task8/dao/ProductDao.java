package by.epam.task8.dao;

import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Product;

import java.util.ArrayList;

public interface ProductDao {
    int addProduct(Product product) throws DaoException;
    ArrayList<Product> extractProductByCategoryId(int categoryId) throws DaoException;
}
