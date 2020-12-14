<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="jdk.internal.misc.FileSystemOption"%>
<%@page import="com.google.appengine.api.datastore.Blob,org.apache.commons.codec.binary.Base64"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header Template</title>
<style>
body {
	background-color: #ECF0F1;
}

header {
	top: -1%;
	background-image: url("/images/home-page-image.gif");
	background-size: cover;
	background-repeat: inherit;
	padding: 5%;
	margin: -10px;
	font-size: xx-large;
	color: whitesmoke;
	border-bottom: 5px solid #cfcfcf;
	position: inherit;
	width: inherit;
	font-family: cursive;
}

.tooltip {  
		height:110px;
		position: absolute; 
		top: 25px; 
		right:0px;
		}
		
		.tooltip .tooltiptext {
		  visibility: hidden;
		  width: 50px;
		  background-color: black;
		  color: #fff;
		  text-align: center;
		  border-radius: 6px;
		  padding: 5px 0;
		  font-size: medium;
		}
		
		.tooltip:hover .tooltiptext {
		  visibility: visible;
		}
		.library{
			color: white;
			text-decoration: none;
		}
		
		
		.profilePic{
		max-width: 100%;
		max-height: 120%;
		border-radius: 50%;
	}
	

</style>
</head>
<body>
	<header>
	<%
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
		<a href="/home" class="library">Library</a> <span style="margin-left: 1100px">
			<div class="tooltip"> 
				<a href="uploadImage" ><img alt="img" class="profilePic"  src="data:image/jpeg;base64, <%= base64Encoded %>" /></a>
		  		<span class="tooltiptext">Upload image</span>
			</div>
		</span>
	</header>
</body>
</html>