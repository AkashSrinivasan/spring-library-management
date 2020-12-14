<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	body {
	  background-color: #d3d3d39e;
	  margin-left: 45px;
	}
	form{
	margin-top: 15px;
	}
</style>
</head>
<body>
		<% 
			if(request.getSession().getAttribute("id") == null ) {
				response.sendRedirect("/");
			} 
			response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		%>
		<center><h1>${message}</h1></center>
		<h1>Hi Admin.!</h1><br>
		
			<a href="addBook">Add a new book to library</a><br><br>
			<a href="displayAllUser">Display all staff and student</a><br><br>
			<a href="displayAllBook">Display all Books</a><br>
		<form action ="logout" method = "POST">
			<input type = "submit" value = "Logout" />
		</form>
</body>
</html>