<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<div align="center">
  <form action="loginRegister" method = "post">
   <table style="with: 100%">
   <tr>
   <td><h3>${message}</h3>
   <h3>${successMessage}</h3>
   </td>
   </tr>
   <tr>
   		<td>
   			<h1>Login Page</h1>
   		</td>
   	</tr>
    <tr>
     <td>UserName</td>
     <td><input type="text" name="username" /></td>
    </tr>
    <tr>
     <td>Password</td>
     <td><input type="password" name="password" /></td>
    </tr>
   </table>
    <br>
   <input type="submit" name="submit" value="login" />
  </form>
 </div>
</body>
</html>