package com.registrationLogin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.registrationLogin.model.LoginRegistrationBean;

public class LoginRegistrationDao {
	
	Connection connection;
	PreparedStatement preparedStatement;
	String passWord = System.getenv("PASS_VAR");
	
	public int addUser(LoginRegistrationBean user){
		
		String INSERT_DATA = "INSERT INTO users" +
	            "  (first_name, last_name, username, password, mobile, address, email) VALUES " +
	            " (?, ?, ?, ?, ?,?,?);";
		
		
		int status = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginRegistration", "root", passWord);
			preparedStatement = connection.prepareStatement(INSERT_DATA);
			
			preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getMobile());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getEmail());
            
            status = preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
            connection.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
		
	}
	
	public LoginRegistrationBean validateUser(String username, String password) {
		LoginRegistrationBean user = new LoginRegistrationBean();
        try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginRegistration", "root", passWord);
			preparedStatement = connection.prepareStatement("select * from users where username = ? and password = ?");
			
			preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
            	user.setFirstName(rs.getString(1));
            	user.setLastName(rs.getString(2));
            	user.setUserName(rs.getString(3));
            	user.setPassword(rs.getString(4));
            	user.setMobile(rs.getString(5));
            	user.setAddress(rs.getString(6));
            	user.setEmail(rs.getString(7));
            }
            connection.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean duplicateCheck(String name) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginRegistration", "root", passWord);
			preparedStatement = connection.prepareStatement("select * from users where username = ?");
			preparedStatement.setNString(1, name);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				return true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
