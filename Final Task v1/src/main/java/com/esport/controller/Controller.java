package main.java.com.esport.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.esport.command.Command;
import main.java.com.esport.controller.exception.CommandNotFoundException;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String COMMAND = "command";

	private static final int PAGE_NOT_FOUND_ERROR = 404;

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet");
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		System.out.println("processRequest");
		String commandName = request.getParameter(COMMAND);
		System.out.println(commandName);
		try {
			Command command = RequestHelper.getInstance().getCommand(commandName);
			command.execute(request, response);
		} catch (CommandNotFoundException e) {
			response.sendError(PAGE_NOT_FOUND_ERROR);
		}
	}

}
