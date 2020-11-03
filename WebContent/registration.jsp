<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<script language = "Javascript"> 
function validate()
{ 
     var firstName = document.form.firstName.value;
     var lastName = document.form.lastName.value;
     var username = document.form.username.value; 
     var password = document.form.password.value;
     var mobile = document.form.mobile.value;
     var address = document.form.address.value;
     var email = document.form.email.value;
     
     if (firstName==null || firstName=="")
     { 
	     alert("First Name can't be blank");
	     /* firstName.focus() */
	     return false; 
     }
     else if (lastName==null || lastName=="")
     { 
	     alert("Last Name can't be blank"); 
	     /* lastName.focus() */
	     return false; 
     }
     else if (username==null || username=="")
     { 
	     alert("Username can't be blank");
	     /* username.focus() */
	     return false; 
     }
     else if(password==null || password =="")
     { 
	     alert("Password must be at least 6 characters long."); 
	     /* password.focus() */
	     return false; 
     }
     else if (mobile==null || mobile=="")
     { 
	     alert("Mobile Number can't be blank");
	     /* mobile.focus() */
	     return false; 
     }
     else if (address==null || address=="")
     { 
	     alert("Address can't be blank"); 
	     /* address.focus() */
	     return false; 
     }
     else if (email==null || email=="")
     { 
	     alert("Email can't be blank"); 
	    /*  email.focus() */
	     return false; 
     }        
 }
</script> 
</head>
<body>
<div align="center">
  <h1>User Registration</h1>
  <form name = "form" action="loginRegister" method = "post" onsubmit="return validate()">
   <table style="with: 80%">
   <tr>
   <td><h3>${message}</h3>
   </td>
   </tr>
    <tr>
     <td>First Name</td>
     <td><input type="text" name="firstName" /></td>
    </tr>
    <tr>
     <td>Last Name</td>
     <td><input type="text" name="lastName" /></td>
    </tr>
    <tr>
     <td>UserName</td>
     <td><input type="text" name="username" /></td>
    </tr>
    <tr>
     <td>Password</td>
     <td><input type="password" name="password" /></td>
    </tr>
    <tr>
    <tr>
     <td>Mobile</td>
     <td><input type="text" name="mobile" /></td>
    </tr>
    <tr>
     <td>Address</td>
     <td><input type="text" name="address" /></td>
    </tr>
    <tr>
     <td>Email</td>
     <td><input type="text" name="email" /></td>
    </tr>
   </table>
   <br>
   <input type="submit" name = "submit" value="Register">
   <p style = "text-align:center">Already Registered?</p>
   <a href = "login.jsp">Login</a>
  </form>
 </div>
</body>
</html>