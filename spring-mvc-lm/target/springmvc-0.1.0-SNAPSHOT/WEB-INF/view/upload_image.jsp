<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload Image</title>
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
	input[type=text], input[type=password],input[type=file]{
		width: 100%;
		margin: 8px 0;
		padding: 12px 20px;
		display: inline-block;
		box-sizing: border-box;
		border: 1px solid black;
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
	.error{
		  color: red;
		  align: center
		  }
</style>
</head>
<body>
	<% 
		if(request.getSession().getAttribute("id") == null ) {
			response.sendRedirect("/");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" ); %>
	<h1>Upload Image</h1>
	<form action="updateImage" method="POST" enctype="multipart/form-data">
		<div class="container">
			<label>Upload New Profile Photo : </label><br>
				<input type="file" name="myFile" required><br><br>
			<button type="submit">Upload</button><br>
		</div> 
	</form><br>

</body>
</html>