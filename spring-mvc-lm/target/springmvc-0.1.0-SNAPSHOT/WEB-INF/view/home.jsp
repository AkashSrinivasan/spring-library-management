<%@page import="jdk.internal.misc.FileSystemOption"%>
<%@page import="com.google.appengine.api.datastore.Blob"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.home.model.User, org.apache.commons.codec.binary.Base64"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
	body {
	  background-color: #d3d3d39e;
	  margin-left: 45px;
	}
	.profilePic{
		max-width: 100%;
		max-height: 120%;
		
	}
	.banner{
		max-width:100%;
	}
	.tooltip {  
		height:150px;
		position: absolute; 
		top: 25px; 
		right: 150px;
		}
		
		.tooltip .tooltiptext {
		  visibility: hidden;
		  width: 120px;
		  background-color: black;
		  color: #fff;
		  text-align: center;
		  border-radius: 6px;
		  padding: 5px 0;
		}
		
		.tooltip:hover .tooltiptext {
		  visibility: visible;
		}
</style>
</head>
<body>
	<% 
		if(request.getSession().getAttribute("id") == null ) {
			response.sendRedirect("/");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
	String userName =(String) request.getSession().getAttribute("name");
	String base64Encoded = null;
	try{
		Blob profilePic= (Blob) request.getSession().getAttribute("profilePic");
		byte[] bytes  = profilePic.getBytes();
	    byte[] encodeBase64 = Base64.encodeBase64(bytes);
	    base64Encoded = new String(encodeBase64, "UTF-8");
	}catch(Exception e){
		response.sendRedirect("/");
	}
    
	%>
	
	<h1>${message} </h1>
	<h1>Hi <%= userName %>.!</h1><br>
	<div class="tooltip"> 
		<a href="uploadImage" ><img alt="img" class="profilePic"  src="data:image/jpeg;base64, <%= base64Encoded %>" /></a>
  		<span class="tooltiptext">Upload image</span>
	</div>
	
		<a href="displayBook">Display borrowed book</a><br>
		<a href="barrowBook">Get book from Library</a><br>
		<a href="returnBook">Return a book to Library</a><br><br>
		<form action ="logout" method = "POST">
			<input type = "submit" value = "Logout" />
		</form>
</body>
</html>