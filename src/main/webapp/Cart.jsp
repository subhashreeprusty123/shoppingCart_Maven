<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="view"%>
    <%@page import="java.util.List" %>
    <%@page import="com.deloitte.Product" %>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
.card-container {
            display: flex;
            flex-wrap: wrap;
            gap: 10px; /* Adjust the gap between cards as needed */
        }
        /* Style for the card container */
        .card {
            width: 300px;
            border: 1px solid #ccc;
            border-radius: 8px;
            overflow: hidden;
            margin: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        /* Style for the card image */
        .card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }

        /* Style for the card content */
        .card-content {
            padding: 16px;
        }

        /* Style for the price */
        .price {
            color: green;
            font-weight: bold;
            margin-bottom: 8px;
        }

        /* Style for the MRPrice */
        .mrp-price {
            text-decoration: line-through;
            color: red;
            margin-bottom: 8px;
        }

        /* Style for the description */
        .description {
            font-size: 14px;
            color: #555;
            margin-bottom: 8px;
        }

        /* Style for the Add to Cart button */
        .add-to-cart {
            background-color: #007bff;
            color: #fff;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.3s ease;
        }

        .add-to-cart:hover {
            background-color: #0056b3;
        }
    </style>
<body>
<div class="card-container">
<view:forEach var="cart" items="${sessionScope.cartItems}">
	  <form action="MyCart" method="post">
	  <div class="card">
	  
        <img src="data:image/jpeg;base64,${cart.getImageBase64()}" alt="Product Image" name="image">
        <div class="card-content">
            <div class="price" name="price"><view:out value="${cart.getPrice()}"></view:out></div>
            <div class="mrp-price" ><view:out value="${cart.getMrpPrice()}"></view:out></div>
            <div class="description">
                <view:out value="${cart.getDescription()}"></view:out>
            </div>
            <button type="submit" class="add-to-cart">Check Out</button>
        </div>
        </form>
    </div>
    </view:forEach>
    </div>
</body>
</html>