package by.epam.task8.dao;

import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.User;

import java.util.ArrayList;

public interface UserDao {
    int addUser(User user) throws DaoException;
    public ArrayList<User> extractUserById(int id) throws DaoException;
}
