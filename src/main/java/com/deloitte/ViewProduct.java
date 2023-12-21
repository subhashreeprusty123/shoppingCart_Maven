package com.deloitte;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewProduct
 */
public class ViewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		List<Product> list = ShoppingDao.getProductList();
		session.setAttribute("ProductList", list);
		resp.sendRedirect("ViewProduct.jsp");
		
	}

}
