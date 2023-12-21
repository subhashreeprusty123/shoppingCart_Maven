package com.deloitte;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.Part;

public class ShoppingDao {
	static Connection conn = null;
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingdb","root","Subha_1006");
			System.out.println("connected sucessfully");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static int checkAdmin(String email, String password) {
		int result =0;
		try {
			Connection conn = ShoppingDao.getConnection();
			PreparedStatement ps= conn.prepareStatement("select * from admin where emailId =? and password = ?");
			
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public static int addCustomer(Customer newCustomer) {
		int result =0;
		try {
			Connection conn = ShoppingDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into customer(name,email,password,phone,gender,address,pincode) values(?,?,?,?,?,?,?);");
			
			ps.setString(1, newCustomer.getName());
			ps.setString(2, newCustomer.getEmail());
			ps.setString(3, newCustomer.getPassword());
			ps.setString(4, newCustomer.getPhone());
			ps.setString(5, newCustomer.getGender());
			ps.setString(6, newCustomer.getAddress());
			ps.setInt(7, newCustomer.getPincode());
			
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static int findCustomer(String email, String password) {
		int result =0;
		try {
			Connection conn = ShoppingDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("select customerId from customer where email=? and password=?");
			
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static int addProduct(Product product,Part part) {
		int result =0;
		try {
			Connection conn =ShoppingDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into products(name,price,description,mrp_price,status,category,file_data) values(?,?,?,?,?,?,?);");
			
			ps.setString(1, product.getName());
			ps.setDouble(2, product.getPrice());
			ps.setString(3, product.getDescription());
			ps.setDouble(4, product.getMrpPrice());
			ps.setString(5, product.getStatus());
			ps.setString(6, product.getCategory());
			//ps.setString(7, product.getFileData());
			if (part != null && part.getSize() > 0) {
	            // Get the input stream of the file
				InputStream inps = part.getInputStream();
	            

	            // Set the input stream as a Blob
	            ps.setBlob(7, inps);
	        }
	        else {
	        	ps.setBlob(7, (Blob)null);
	        }
			System.out.println(product);
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static List<Product> getProductList(){
		List<Product> list = new ArrayList();
		try {
			Connection conn = ShoppingDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from products");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int productid= rs.getInt(1);
				String name = rs.getString(2);
				double price= rs.getDouble(3);
				String desc = rs.getString(4);
				double mrp = rs.getDouble(5);
				String status = rs.getString(6);
				String category = rs.getString(7);
				Blob imageBlob = rs.getBlob(8);
				String image=null;
				try {
		            if (imageBlob != null) {
		                byte[] bytes = imageBlob.getBytes(1, (int) imageBlob.length());
		                image =  Base64.getEncoder().encodeToString(bytes);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
				Product product1 = new Product(productid,name,price,desc,mrp,status,category,imageBlob);
				product1.setImageBase64(image);
				System.out.println("Dao " + product1);
				System.out.println("length of image " + product1.getImageBase64().length());
				list.add(product1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return list;	
	}
	
	public static int addToCart(int cid,int pid) {
		int result =0;
		try {
			Connection conn = ShoppingDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into shopping_cart(customerId,product_id) values(?,?);");
			
			ps.setInt(1, cid);
			ps.setInt(2, pid);
			result = ps.executeUpdate();
			System.out.println("added successfully");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	public static List<Product> showCart(int cid) {
		List<Product> list = new ArrayList();
		try {
			Connection conn = ShoppingDao.getConnection();
			PreparedStatement ps = conn.prepareStatement("select products.productid , products.name,products.price,\r\n"
					+ "products.description,products.mrp_price, products.status, \r\n"
					+ "products.category,products.file_data from shopping_cart join customer \r\n"
					+ "on ? = shopping_cart.customerId \r\n"
					+ "join products on products.productid = shopping_cart.product_id");
			ps.setInt(1, cid);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int pid= rs.getInt(1);
				String name = rs.getString(2);
				double price = rs.getDouble(3);
				String desc = rs.getString(4);
				double mrp = rs.getDouble(5);
				String status =rs.getString(6);
				String category = rs.getString(7);
				Blob imageBlob = rs.getBlob(8);
				String image=null;
				try {
		            if (imageBlob != null) {
		                byte[] bytes = imageBlob.getBytes(1, (int) imageBlob.length());
		                image =  Base64.getEncoder().encodeToString(bytes);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
				Product product2 = new Product(pid,name,price,desc,mrp,status,category,imageBlob);
				product2.setImageBase64(image);
				list.add(product2);
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}
