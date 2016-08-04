package main.java.com.esport.service.impl;
import main.java.com.esport.dao.exception.DaoException;
import main.java.com.esport.dao.factory.DaoFactory;
import main.java.com.esport.dao.interfaces.UserDao;
import main.java.com.esport.domain.User;
import main.java.com.esport.domain.UserRole;
import main.java.com.esport.service.exception.ServiceException;
import main.java.com.esport.service.exception.ServiceWrongEmailException;
import main.java.com.esport.service.exception.ServiceWrongPasswordException;
import main.java.com.esport.service.factory.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User authorisation(String email, String password) throws ServiceException {
		try {
			
			DaoFactory daoFactory = DaoFactory.getInstance();
			UserDao userDao = daoFactory.getUserDao();
			User user = userDao.getUserByEmail(email);
			if (user == null) {
				throw new ServiceWrongEmailException("Wrong email");
			}
			if (!user.getPassword().equals(password)) {
				throw new ServiceWrongPasswordException("Wrong password");
			}
			return user;
		} catch (DaoException e) {
			throw new ServiceException("Service layer: cannot make a login operation", e);
		}
	}

	// role srting or enum
	@Override
	public User registration(String email, String password, String role, String name) throws ServiceException {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			UserDao userDAO = daoFactory.getUserDao();

			User userWithThisEmail = userDAO.getUserByEmail(email);
			if (userWithThisEmail != null) {
				throw new ServiceWrongEmailException("Wrong email");
			}

			User newUser = new User();
			newUser.setEmail(email);
			newUser.setPassword(password);
			newUser.setRole(UserRole.valueOf(role));
			newUser.setName(name);
			userDAO.addUser(newUser);

			return newUser;
		} catch (DaoException e) {
			throw new ServiceException("Service layer: cannot make a registration", e);
		}
	}

}
