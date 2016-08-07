package main.java.com.esport.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.com.esport.command.Command;
import main.java.com.esport.domain.User;
import main.java.com.esport.service.UserService;
import main.java.com.esport.service.exception.ServiceException;
import main.java.com.esport.service.exception.ServiceWrongEmailException;
import main.java.com.esport.service.exception.ServiceWrongPasswordException;
import main.java.com.esport.service.factory.ServiceFactory;

public class LoginCommand implements Command {

	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String USER_ID = "userId";
	private static final String USER_NAME = "userName";
	private static final String USER_ROLE = "userRole";
	private static final String WRONG_EMAIL = "wrongEmail";
	private static final String WRONG_PASSWORD = "wrongPassword";
	private static final String SERVICE_ERROR = "serviceError";
	private static final String HELLO_JSP = "WEB-INF/jsp/hello.jsp";
	private static final String LOGIN_JSP = "WEB-INF/jsp/login.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String login = request.getParameter(EMAIL);
		String password = request.getParameter(PASSWORD);

		if (login == null && password == null) {
			request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
		}

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		try {
			User user = userService.authorisation(login, password);
			HttpSession session = request.getSession(false);
			session.setAttribute(USER_ID, user.getId());
			session.setAttribute(USER_NAME, user.getName());
			session.setAttribute(EMAIL, user.getEmail());
			session.setAttribute(USER_ROLE, user.getRole());
			request.getRequestDispatcher(HELLO_JSP).forward(request, response);
		} catch (ServiceWrongEmailException e) {
			generateErrorMessage(request, response,WRONG_EMAIL);
		} catch (ServiceWrongPasswordException e) {
			generateErrorMessage(request, response,WRONG_PASSWORD);
		} catch (ServiceException e) {
			request.setAttribute(SERVICE_ERROR, true);
			request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
		}
	}

	private void generateErrorMessage(HttpServletRequest request, HttpServletResponse response, String errorName) throws ServletException, IOException {
		request.setAttribute(errorName, true);
		request.setAttribute(EMAIL, request.getParameter(EMAIL));
		request.setAttribute(PASSWORD, request.getParameter(PASSWORD));
		request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
	}
}
