package com.registrationLogin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.registrationLogin.dao.LoginRegistrationDao;
import com.registrationLogin.model.LoginRegistrationBean;


@WebServlet("/loginRegister")
public class LoginRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public LoginRegisterServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LoginRegistrationDao userDAO = new LoginRegistrationDao();
		
		String name = request.getParameter("username");
        String password = request.getParameter("password");
        String submitType = request.getParameter("submit");

        LoginRegistrationBean user = userDAO.validateUser(name, password);
        
        switch(submitType) {
        case "Register":
        	user.setFirstName(request.getParameter("firstName"));
        	user.setLastName(request.getParameter("lastName"));
        	user.setUserName(name);
        	user.setPassword(password);
        	user.setMobile(request.getParameter("mobile"));
        	user.setAddress(request.getParameter("address"));
        	user.setEmail(request.getParameter("email"));
        	
        	userDAO.addUser(user);
        	request.setAttribute("successMessage", "Registered succesfully, please login to continue");
        	request.getRequestDispatcher("login.jsp").forward(request,response);	
        	
        	break;
        
        case "login":
        	
        	if(user != null && user.getUserName() != null) {
//        		request.setAttribute("message", user.getFirstName());
//        		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/welcome.jsp");
//        		dispatcher.forward(request, response);
            	request.setAttribute("message", user.getLastName());
            	request.getRequestDispatcher("welcome.jsp").forward(request,response);		
            } else {
            	request.setAttribute("message", "Data not found, Complete registration");
            	request.getRequestDispatcher("registration.jsp").forward(request,response);	
            }
        	break;
        	
        default:
        	request.setAttribute("message", "Data not found, Complete registration");
        	request.getRequestDispatcher("registration.jsp").forward(request,response);	
        }
	}

}
