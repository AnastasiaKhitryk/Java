package by.epam.task8.dao;

import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.Model;

import java.util.ArrayList;

public interface CommonDao<T extends Model> {
    ArrayList<T> getById(int id) throws DaoException;
}
