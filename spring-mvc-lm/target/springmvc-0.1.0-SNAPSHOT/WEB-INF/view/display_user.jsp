<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
		body {
		  background-color: #d3d3d39e;
		  margin-left: 20px;
		}
		table, th, td {
		  border: 1px solid black;
		  }
		  table{
		  width: 400px;
   			 text-align: center;
		  }
		  .col1{
		  width:30%}
		  .col2{
		  width:30%}
		  .col3{
		  width:40%}
		 img{
		 height:150px;
</style>
</head>
<body>
	
		<% 
		if(request.getSession().getAttribute("id") == null ) {
			response.sendRedirect("/");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		%>
		<h1><div id="studentlist-header"></div></h1>
		<table id="studentTable">
		<thead>
		  <tr>
			  <th>id</th>
			  <th>name</th>
		  </tr>
		  </thead>
		  <tbody id="myStudentTable"></tbody>
		</table>
		
		
		<h1><div id="stafflist-header"></div></h1>
		<table id="staffTable">
		<thead>
		  <tr>
			  <th>ID</th>
			  <th>Name</th>
		  </tr>
		  </thead>
		  <tbody id="myStaffTable"></tbody>
		</table>
		<a href="adminHome">back</a>

</body>
<script>


function loadStudentList() {
	  return new Promise((resolve, reject) => {
	    $.ajax({
	      url: "/getAllStudent",
	      type: 'GET',
	      contentType:'application/json' ,
	      data: {
	        key: 'value',
	      },
	      success: function (data) {
	        resolve(data)
	      },
	      error: function (error) {
	        reject(error)
	      },
	    })
	  }).then((data) => {
		  if(data.length == 0){
		    	document.getElementById("studentTable").style.display = "none";
		    	document.getElementById("studentlist-header").innerHTML = "Student List is empty.!"
		    	}else{
		    		
		    		data.forEach(element => {
			    		document.getElementById("studentlist-header").innerHTML = "Student List"
			    		var table = document.getElementById("myStudentTable");
			    		 var row = table.insertRow(0);
			    		  var cell1 = row.insertCell(0);
			    		  var cell2 = row.insertCell(1);
			    		  cell1.innerHTML = element.id;
			    		  cell2.innerHTML = element.name;
				  });
		    	}
	  })
	  .catch((error) => {
	    console.log(error)
	  })
	}
	
function loadStaffList() {
	  return new Promise((resolve, reject) => {
	    $.ajax({
	      url: "/getAllStaff",
	      type: 'GET',
	      contentType:'application/json' ,
	      data: {
	        key: 'value',
	      },
	      success: function (data) {
	        resolve(data)
	      },
	      error: function (error) {
	        reject(error)
	      },
	    })
	  }).then((data) => {
		  if(data.length == 0){
		    	document.getElementById("staffTable").style.display = "none";
		    	document.getElementById("stafflist-header").innerHTML = "Staff List is empty.!"
		    	}else{
		    		
		    		data.forEach(element => {
			    		document.getElementById("stafflist-header").innerHTML = "Staff List"
			    		var table = document.getElementById("myStaffTable");
			    		 var row = table.insertRow(0);
			    		  var cell1 = row.insertCell(0);
			    		  var cell2 = row.insertCell(1);
			    		  cell1.innerHTML = element.id;
			    		  cell2.innerHTML = element.name;
				  });
		    	}
	  })
	  .catch((error) => {
	    console.log(error)
	  })
	}
	
	loadStudentList();
	loadStaffList();
	
</script>
</html>