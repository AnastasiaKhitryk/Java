package main.java.com.esport.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.com.esport.command.Command;
import main.java.com.esport.domain.User;
import main.java.com.esport.service.exception.ServiceException;
import main.java.com.esport.service.exception.ServiceWrongEmailException;
import main.java.com.esport.service.exception.ServiceWrongPasswordException;
import main.java.com.esport.service.factory.ServiceFactory;
import main.java.com.esport.service.factory.UserService;

public class LoginCommand implements Command {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			System.out.println(login);
			System.out.println(password);
			if(login!=null && password!=null){
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				UserService userService = serviceFactory.getUserService();
				try{
					User user = userService.authorisation(login, password);
					HttpSession session = request.getSession(true);
					session.setAttribute("userId", user.getId());
					session.setAttribute("userName", user.getName());
					session.setAttribute("userEmail", user.getEmail());
	                session.setAttribute("userRole", user.getRole());
	                response.sendRedirect("/Controller?command=welcome");
				}catch (ServiceWrongEmailException e) {
					request.setAttribute("wrongEmail", true);
	                request.setAttribute("login", login);
	                request.setAttribute("password", password);
	                request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
				} catch (ServiceWrongPasswordException e) {
	                request.setAttribute("wrongPassword", true);
	                request.setAttribute("login", login);
	                request.setAttribute("password", password);
	                request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	            } catch (ServiceException e) {
	                request.setAttribute("serviceError", true);
	                request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	            }
			}//timeout	
			else{
                request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
			}
	}
}
