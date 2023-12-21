package com.deloitte;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyCart
 */
public class MyCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int cid =(Integer) (session.getAttribute("cid"));
		
		List<Product> list = ShoppingDao.showCart(cid);
		session.setAttribute("cartItems", list);
		response.sendRedirect("Cart.jsp");
	}

}
