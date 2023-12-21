<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }
 
        .navbar {
            background-color: #333;
            overflow: hidden;
        }
 
        .navbar a {
            float: left;
            display: block;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }
 
        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }
 
        .logout {
            float: right;
        }
    </style>
<body>
   <div class="navbar">
        <a href="">Home</a>
        <a href="AddProduct.jsp">Add Products</a>
        <a href="ViewProduct.jsp">View Products</a>
        <a href="" class="logout">Logout</a>
    </div>

</body>
</html>