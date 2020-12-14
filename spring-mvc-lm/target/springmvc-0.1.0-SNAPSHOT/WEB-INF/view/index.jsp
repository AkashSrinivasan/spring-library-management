<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style> 
    Body {
    font-family: Calibri, Helvetica, sans-serif;
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
    input[type=text], input[type=password],input[type=number] { 
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
            background-color: lightgray;
        } 
    .error{
      color: red;
      align: center
    }
    #message {  
        /* height:150px; */
        position: absolute; 
        top: 50px; 
        right: 150px;
        border: 1px solid black;
        text-align: center;
        
    }

    .tooltip{
        position: relative;
    }
    .tooltiptext::after {
        content:'';
            display:block;
            width:0;
            height:0;
            position:absolute;
            
            border-top: 8px solid transparent;
            border-bottom: 8px solid transparent; 
            border-right:8px solid black;
            left:-8px;
            
            top:90px;
        }
    .tooltiptext{
        visibility: hidden;
        width: 200px;
        background-color: black;
        color: #fff;
        /* text-align: center; */
        border-radius: 6px;
        padding: 5px 0;
        
        /* Position the tooltip */
        position: absolute;
        z-index: 1;
        top: -70px;
        left: 103%;
        opacity: 0.6;
    }

    .valid {
    color: green;
    }

    .valid:before {
    position: relative;
    left: -10px;
    content: "\2713";
    }

            
            .invalid {
    color: red;
    }

    .invalid:before {
    position: relative;
    left: -10px;
    content: "\2613";
    }

    p{
        padding-left: 20px;
    }
    h1{
    	text-align: center;}
</style> 
</head>
<body>
	
	<h1>${message}</h1>
	<h1 align="center"> Login Form </h1> 
    <form action="login" method="POST">
        <div class="container"> 
            <label>Id : </label> 
            <input type="number" placeholder="Enter Id" name="id" required>
            <label>Password : </label> 
            <div class="tooltip">
                <input id="password" type="password" placeholder="Enter Password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required>
                <span class="tooltiptext" id="tooltiptext">
                    <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
                    <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
                    <p id="number" class="invalid">A <b>number</b></p>
                    <p id="length" class="invalid">Minimum <b>8 characters</b></p>
                </span>
            </div>
            <input type="checkbox" onclick="showPassword()">Show Password 
            <br><br>
            <label>Select : </label>
            <select name="user" id="user">
			    <option value="Student">Student</option>
			    <option value="Staff">Staff</option>
			    <option value="Admin">Admin</option>
			 </select>
            <button type="submit">Login</button> 
            New <a href="signup"> Signup </a><br>
        </div> 
    </form>  
    
    <script type="text/javascript">
    function showPassword() {
    	  var x = document.getElementById("password");
    	  if (x.type === "password") {
    	    x.type = "text";
    	  } else {
    	    x.type = "password";
    	  }
    	}
        var passwordField = document.getElementById('password');
    var letter = document.getElementById("letter");
    var capital = document.getElementById("capital");
    var number = document.getElementById("number");
    var length = document.getElementById("length");

    passwordField.onblur = function(){
        document.getElementById('tooltiptext').style.visibility =  "hidden";
    }

    passwordField.onfocus = function(){
        document.getElementById('tooltiptext').style.visibility =  "visible";
    }

    passwordField.onkeyup = function() {
        var lowerCaseLetters = /[a-z]/g;
        if(passwordField.value.match(lowerCaseLetters)) {  
            letter.classList.remove("invalid");
            letter.classList.add("valid");
        } else {
            letter.classList.remove("valid");
            letter.classList.add("invalid");
        }
        
        var upperCaseLetters = /[A-Z]/g;
        if(passwordField.value.match(upperCaseLetters)) {  
            capital.classList.remove("invalid");
            capital.classList.add("valid");
        } else {
            capital.classList.remove("valid");
            capital.classList.add("invalid");
        }

        var numbers = /[0-9]/g;
        if(passwordField.value.match(numbers)) {  
            number.classList.remove("invalid");
            number.classList.add("valid");
        } else {
            number.classList.remove("valid");
            number.classList.add("invalid");
        }

        if(passwordField.value.length >= 8) {
            length.classList.remove("invalid");
            length.classList.add("valid");
        } else {
            length.classList.remove("valid");
            length.classList.add("invalid");
        }
    }
    </script>

</body>
</html>