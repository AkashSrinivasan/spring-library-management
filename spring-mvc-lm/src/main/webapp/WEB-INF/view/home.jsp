<%@page import="jdk.internal.misc.FileSystemOption"%>
<%@page import="com.google.appengine.api.datastore.Blob"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.home.model.User, org.apache.commons.codec.binary.Base64"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
	.body {
	  margin-left: 45px;
	}
	
		.flip-card {
  background-color: transparent;
  width: 300px;
  height: 300px;
  perspective: 1000px;
}

.flip-card-inner {
  position: relative;
  width: 100%;
  height: 100%;
  text-align: center;
  transition: transform 0.6s;
  transform-style: preserve-3d;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
}

.flip-card:hover .flip-card-inner {
  transform: rotateY(180deg);
}

.flip-card-front, .flip-card-back {
  position: absolute;
  width: 100%;
  height: 100%;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}

.flip-card-front {
  background-color: #bbb;
  color: black;
}

.flip-card-back {
  background-color: #2980b9;
  color: white;
  transform: rotateY(180deg);
}
		.home-card{
		display: flex;
  justify-content: space-between;
  }
  h1{
  text-align: center;}
  .logout{
      margin-top: 100px;
    }
    .logout-button{
    width: 70%; 
    align-items: center; 
    border: 2px solid black;
  color: black;
  padding: 14px 28px;
  font-size: 16px;
  cursor: pointer;
  text-align: center; }
  .logout-button:hover {
	background-color: #ddd;
  color: black;
}
</style>
</head>
<body>

	<%@ include file="/WEB-INF/view/header.jsp"%>
	
	<% 
		if(request.getSession().getAttribute("id") == null ) {
			response.sendRedirect("/");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
	String userName =null;
	
	try{
		userName =(String) request.getSession().getAttribute("name");
		if(userName == null){
			response.sendRedirect("/");
		}
	}catch(Exception e){
		response.sendRedirect("/");
	}
    
	%>
	
	<div class="body">
	
	<h1>${message} </h1>
	<span><h1>Welcome <%= userName %>.!</h1>
	
	</span>
	
	
	
	<div class="home-card">
	
	<div class="flip-card">
  <div class="flip-card-inner">
    <div class="flip-card-front">
      <img src="/images/displayBook.jpg" alt="Avatar" style="width:300px;height:300px;">
    </div>
    <div class="flip-card-back">
      <h1>Display Book</h1> 
      <p>Your borrowed book list</p> 
      <p>To view the list</p>
      <a href="displayBook">click here</a>
    </div>
  </div>
</div>


<div class="flip-card">
  <div class="flip-card-inner">
    <div class="flip-card-front">
      <img src="/images/getBook.jpg" alt="Avatar" style="width:300px;height:300px;">
    </div>
    <div class="flip-card-back">
      <h1>Get Book</h1> 
      <p>Get a book to read</p> 
      <p>To get a book</p>
      <a href="barrowBook">click here</a>
    </div>
  </div>
</div>


<div class="flip-card">
  <div class="flip-card-inner">
    <div class="flip-card-front">
      <img src="/images/returnBook.jpg" alt="Avatar" style="width:300px;height:300px;">
    </div>
    <div class="flip-card-back">
      <h1>Return Book</h1> 
      <p>Return the book once you completed</p> 
      <p>To return the book</p>
      <a href="returnBook">click here</a>
    </div>
  </div>
</div>
</div>

<form class="logout" align="center" action ="logout" method = "POST">
			<input type = "submit" value = "Logout" class="logout-button"/>
</form>

</body>
</html>