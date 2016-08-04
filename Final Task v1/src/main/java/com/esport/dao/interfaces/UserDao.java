package main.java.com.esport.dao.interfaces;

import java.util.List;

import main.java.com.esport.dao.exception.DaoException;
import main.java.com.esport.domain.User;

public interface UserDao {
	void addUser(User user) throws DaoException;
	void updateUser(User user) throws DaoException;
	void deleteUser(int id) throws DaoException;
	List<User> getAllUsers() throws DaoException;
	User getUserById(int id) throws DaoException;
	User getUserByEmail(String email) throws DaoException;
}
