package com.deloitte;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ProductAdd
 */
@WebServlet("/ProductAdd")
@MultipartConfig
public class ProductAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String name = request.getParameter("name");
		Double price = Double.parseDouble((String)request.getParameter("price"));
		String desc = request.getParameter("description");
		Double mrpPrice = Double.parseDouble((String)request.getParameter("mrp"));
		String status = request.getParameter("status");
		String category = request.getParameter("category");
		
		Part part = request.getPart("image");
//		InputStream imageStream = part.getInputStream();
//        String imageName = part.getSubmittedFileName();
//        System.out.println(imageName);
//        InputStream uploadpath="C:\\Users\\subprusty\\eclipse-workspace\\Shopping-Application\\src\\main\\webapp\\image\\"+ imageName;
//        System.out.println(uploadpath);
        
      
		InputStream inputStream = part.getInputStream();
        
        
        Product product = new Product(name,price,desc,mrpPrice,status,category,inputStream);
       int result=  ShoppingDao.addProduct(product,part);
       if(result >0) {
    	   List<Product> list = ShoppingDao.getProductList();
			 
			 session.setAttribute("ProductList", list);
			 System.out.print(list);
    	   response.sendRedirect("ViewProduct.jsp");
       }else {
    	   out.print("<h3>product not added</h3>");
       }
	}

}
