<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userValidation") == null){
	response.sendRedirect("login.jsp");
}else user = (String) session.getAttribute("userValidation");
/* String user = (String) session.getAttribute("userValidation"); */
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) 
		userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) 
		sessionID = cookie.getValue();
}
}else{
	sessionID = session.getId();
}
%>
<%-- <h3>Hi ${message} , Welcome to the Blog!!!!!! Your Session ID=<%=sessionID %></h3> --%>
<h3>Hi <%=userName %> , Welcome to the Blog!!!!!! Your Session ID=<%=sessionID %></h3>
<br>User=<%=user %><br>
<form action="<%=response.encodeURL("LogoutServlet") %>" method="post">
<!-- <form action="LogoutServlet" method="post"> -->
<input type="submit" value="Logout" >
</form>
</body>
</html>