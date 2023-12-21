package com.deloitte;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class Product {
	private int id;
    private String name;
    private double price;
    private String description;
    private double mrpPrice;
    private String status;
    private String category;
    private InputStream fileData; // Assuming file_data is stored as InputStream in the database
    private String imageBase64 ;
   
 
    public Product() {
    }
 
    public Product(String name, double price, String description, double mrpPrice, String status, String category,
    		InputStream fileData) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.mrpPrice = mrpPrice;
        this.status = status;
        this.category = category;
        this.fileData = fileData;
    }
 

 
    public Product(int productid, String name2, double price2, String desc, double mrp, String status2,
			String category2, Blob imageBlob) {
    	this.id=productid;
        this.name = name2;
        this.price = price2;
        this.description = desc;
        this.mrpPrice = mrp;
        this.status = status2;
        this.category = category2;
        if (imageBlob != null) {
            try {
                // Convert Blob to InputStream
                InputStream fileData = imageBlob.getBinaryStream();
                // Use the InputStream as needed in your logic
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
	}
    

	

	public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public double getMrpPrice() {
        return mrpPrice;
    }
 
    public void setMrpPrice(double mrpPrice) {
        this.mrpPrice = mrpPrice;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public String getCategory() {
        return category;
    }
 
    public void setCategory(String category) {
        this.category = category;
    }
 
    public InputStream getFileData() {
        return fileData;
    }
 
    public void setFileData(InputStream fileData) {
        this.fileData = fileData;
    }

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", description=" + description + ", mrpPrice=" + mrpPrice
				+ ", status=" + status + ", category=" + category + ", fileData=" + fileData + "]";
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
    
}
