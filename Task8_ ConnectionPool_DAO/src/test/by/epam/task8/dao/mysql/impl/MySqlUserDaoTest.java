package by.epam.task8.dao.mysql.impl;

import by.epam.task8.dao.UserDao;
import by.epam.task8.dao.exception.DaoException;
import by.epam.task8.dao.factory.DaoFactory;
import by.epam.task8.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MySqlUserDaoTest {

    @Test
    public void userShouldBeAdded() throws DaoException {
        DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);
        UserDao userDao = daoFactory.getUserDAO();

        User expectedUser = new User();
        expectedUser.setEmail("my@mail.ru");
        expectedUser.setPassword("qwerty");
        expectedUser.setRole("ADMIN");

        userDao.addUser(expectedUser);
        List<User> allUser = userDao.getAllUser();
        User actualUser = allUser.get(allUser.size() - 1);

        Assert.assertEquals(expectedUser.getEmail(),actualUser.getEmail());
        Assert.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        Assert.assertEquals(expectedUser.getRole(),actualUser.getRole());
    }
}


