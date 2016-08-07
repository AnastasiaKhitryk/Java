package main.java.com.esport.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.com.esport.command.Command;

public class LogoutCommand implements Command{
    private static final String USER_ID = "userId";
    private static final String USER_ROLE = "userRole";

    private static final String LOGIN_PAGE_CAUSE_TIMEOUT = "/Controller?command=login&cause=timeout";
//    private static final String SESSION_PREV_QUERY = "prevQuery";
//    private static final String WELCOME_PAGE = "/Controller?command=welcome";
    
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
        if(session == null){
            response.sendRedirect(LOGIN_PAGE_CAUSE_TIMEOUT);
        }
        else {
            session.setAttribute(USER_ID, null);
            session.setAttribute(USER_ROLE, null);

            session.invalidate();  
            request.getRequestDispatcher("index.jsp").include(request, response);  
            
            
//            String prevQuery = (String) session.getAttribute(SESSION_PREV_QUERY);
//            if(prevQuery == null){
//                prevQuery = WELCOME_PAGE;
//            }
//            response.sendRedirect(prevQuery);
        }
	}

}
