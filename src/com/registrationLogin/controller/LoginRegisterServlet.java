package com.registrationLogin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        
        boolean check = userDAO.duplicateCheck(name);
        
        LoginRegistrationBean user = userDAO.validateUser(name, password);
        
        switch(submitType) {
        case "Register":
        	user.setFirstName(request.getParameter("firstName"));
        	user.setLastName(request.getParameter("lastName"));
        	if(!check) {
        		user.setUserName(name);
        	}
        	else {
                request.setAttribute("message", "User Already Exists");
                request.getRequestDispatcher("registration.jsp").forward(request,response);
                break;
        	}
//        	user.setUserName(name);
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
        		HttpSession session = request.getSession();
    			//setting session to expiry in 5 mins
    			session.setMaxInactiveInterval(5*60);
    			session.setAttribute("userValidation", name);
    			Cookie userName = new Cookie("user", name);
    			response.addCookie(userName);
    			String encodedURL = response.encodeRedirectURL("welcome.jsp");
    			response.sendRedirect(encodedURL);
//        		session.setMaxInactiveInterval(30*60);
//    			Cookie userName = new Cookie("user", name);
//    			userName.setMaxAge(30*60);
//    			response.addCookie(userName);
//    			response.sendRedirect("welcome.jsp");
            } else {
            	request.setAttribute("message", "Please Complete the registration before Logging in!!!");
            	request.getRequestDispatcher("registration.jsp").forward(request,response);	
            }
        	break;
        	
        default:
        	request.setAttribute("message", "Data not found, Complete registration");
        	request.getRequestDispatcher("registration.jsp").forward(request,response);	
        }
	}

}
