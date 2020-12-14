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
		 table {
		  width:500px;
		  text-align:center;
		 }
		 
</style>
</head>
<body>

	<% 
		if(request.getSession().getAttribute("id") == null ) {
			response.sendRedirect("/");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" ); %>
		
		<h1><div id="booklist-header"></div></h1> 
		<table id="bookTable">
		<thead>
		  <tr>
			  <th>ID</th>
			  <th>Name</th>
			  <th>Barrower Id</th>
			  <th>Barrowed on</th>
			  <th>valid Till</th>
		  </tr>
		  </thead>
		  <tbody id="bookBody"></tbody>
		</table><br>
		
		<a href="adminHome">back</a>
</body>
<script>


function loadStudentList() {
	  return new Promise((resolve, reject) => {
	    $.ajax({
	      url: "/getAllBook",
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
		  console.log(data);
		  if(data.length == 0){
		    	document.getElementById("bookTable").style.display = "none";
		    	document.getElementById("booklist-header").innerHTML = "Book List is empty.!"
		    	}else{
		    		
		    		data.forEach(element => {
			    		document.getElementById("booklist-header").innerHTML = "Book List"
			    		var table = document.getElementById("bookBody");
			    		var row = table.insertRow(0);
			    		  var cell1 = row.insertCell(0);
			    		  var cell2 = row.insertCell(1);
			    		  var cell3 = row.insertCell(2);
			    		  var cell4 = row.insertCell(3);
			    		  var cell5 = row.insertCell(4);
			    		  cell1.innerHTML = element.id;
			    		  cell2.innerHTML = element.name;
			    		  cell3.innerHTML = element.barrowerId==0?"Nil":element.barrowerId;
				    		cell4.innerHTML = element.borrowedOn;
				    		cell5.innerHTML = element.dueDate;
				  });
		    	}
	  })
	  .catch((error) => {
	    console.log(error)
	  })
	}
	loadStudentList();
	
</script>
</html>