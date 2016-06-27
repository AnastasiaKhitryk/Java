package by.epam.task8.dao;

import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Category;

import java.util.ArrayList;
import java.util.List;

public interface CategoryDao extends CommonDao<Category>{
    List<Category> getAllCategory() throws DaoException;
}
