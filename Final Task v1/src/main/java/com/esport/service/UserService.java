package main.java.com.esport.service;

import main.java.com.esport.domain.User;
import main.java.com.esport.service.exception.ServiceException;

public interface UserService {
	User authorisation(String login, String password) throws ServiceException;
	User registration(String email, String password, String name) throws ServiceException;
}
