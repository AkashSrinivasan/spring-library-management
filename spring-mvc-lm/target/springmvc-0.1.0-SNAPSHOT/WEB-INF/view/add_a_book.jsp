<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	
	h1{
		text-align: center;
	}
	Body {
		font-family: sans-serif;
		}
	button {
		width: 100%;
		padding: 15px;
		margin: 10px 0px; 
		border: none;
		cursor: pointer;
		}
	form {
		border: 3px solid black;
		margin-left: 25%;
		margin-right: 25%;
		}
	input[type=text], input[type=password],input[type=number]{
		width: 100%;
		margin: 8px 0;
		padding: 12px 20px;
		display: inline-block;
		box-sizing: border-box;
		}
	button:hover {
		opacity: 0.7;
		}
	.cancelbtn {
		width: auto;
		padding: 10px 18px;
		margin: 10px 5px;
		}
	.container {
		padding: 25px; 
		background-color: #d3d3d39e;
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
		<h1>${message }</h1>
		<center><h1>Add Book</h1></center>
		<form action="addNewBook" method="POST">
		<div class="container">
				<label>Book Id : </label>
				<input type="text" placeholder="Enter Book Id" name="bookId" required><br>
				<label>Book Name : </label>
				<input type="text" placeholder="Enter Book Name" name="bookName" required><br>
				<button type="submit">Add</button><br>
				<a href="adminHome">Cancel</a>
		</div>
		</form><br>

</body>
</html>