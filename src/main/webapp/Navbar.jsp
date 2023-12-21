<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NavBar</title>
</head>
 <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        nav {
            background-color: #333;
            overflow: hidden;
        }

        nav a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        nav a:hover {
            background-color: #ddd;
            color: black;
        }
         body {
            background-image: url("image/image1.jpg");
            background-size: cover; 
            background-position: center; 
            background-repeat: no-repeat; 
            margin: 0; 
            padding: 0; 
            font-family: Arial, sans-serif;
        }
        .content {
            padding: 20px;
            color: #fff; 
        }
    </style>
<body>
	<nav>
    <a href="#home">Home</a>
    <a href="ViewProduct">Products</a>
    <a href="CustomerLogin.jsp">Customer Login</a>
    <a href="NewCustomer.jsp">Customer Register</a>
    <a href="AdminLogin.jsp">Admin Login</a>
    <a href="MyCart">Cart</a>
</nav>
<div class="content">
    
</div>
</body>
</html>