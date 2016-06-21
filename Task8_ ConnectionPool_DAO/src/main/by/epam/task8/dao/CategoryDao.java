package by.epam.task8.dao;

import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Category;

import java.util.ArrayList;

public interface CategoryDao extends CommonDao<Category>{
    ArrayList<Category> getAllCategory() throws DaoException;
}
