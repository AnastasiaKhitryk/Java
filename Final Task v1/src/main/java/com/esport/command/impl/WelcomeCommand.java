package main.java.com.esport.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.esport.command.Command;

public class WelcomeCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		      //TODO
		        request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
		    }

}
