package by.epam.task8.dao;

import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.entity.User;

import java.util.List;

public interface UserDao extends CommonDao<User>{
    int addUser(User user) throws DaoException;
    List<User> getAllUser() throws DaoException;
}
