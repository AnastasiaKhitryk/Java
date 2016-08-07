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
import main.java.com.esport.service.factory.ServiceFactory;

public class RegistrationCommand implements Command{
	
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String USER_ID = "userId";
	private static final String USER_NAME = "userName";
	private static final String USER_ROLE = "userRole";
	private static final String WRONG_EMAIL = "wrongEmail";
	private static final String SERVICE_ERROR = "serviceError";
	private static final String REGISTRATION_JSP = "WEB-INF/jsp/registration.jsp";
	private static final String COMMAND_WELCOME ="/Controller?command=welcome";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(USER_NAME);

        if(email == null || password == null ||
                name == null){
        	request.getRequestDispatcher(REGISTRATION_JSP).forward(request, response);
        }
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        try {
            User user = userService.registration(email,password, name);
            HttpSession session = request.getSession(true);
            session.setAttribute(USER_ID, user.getId());
            session.setAttribute(USER_ROLE, user.getRole());
            response.sendRedirect(COMMAND_WELCOME);
        } catch (ServiceWrongEmailException e) {
            //String languageId = LanguageUtil.getLanguageId(request);
            //setLocaleAttributes(request, languageId);
            generateErrorMessage(request,response,WRONG_EMAIL);
        } catch (ServiceException e) {
            //String languageId = LanguageUtil.getLanguageId(request);
            //setLocaleAttributes(request, languageId);
        	generateErrorMessage(request,response,SERVICE_ERROR);
        }
	}
	private void generateErrorMessage(HttpServletRequest request, HttpServletResponse response, String errorName) throws ServletException, IOException {
		request.setAttribute(errorName, true);
		request.setAttribute(EMAIL, request.getParameter(EMAIL));
		request.setAttribute(PASSWORD, request.getParameter(PASSWORD));
		request.setAttribute(USER_NAME, request.getParameter(USER_NAME));
		request.getRequestDispatcher(REGISTRATION_JSP).forward(request, response);
	}

}
