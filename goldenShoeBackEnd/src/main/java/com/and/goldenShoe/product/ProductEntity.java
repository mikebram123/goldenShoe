package com.and.goldenShoe.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlTransient;


import com.and.goldenShoe.productSizeAssignment.ProductSizeAssignmentEntity;

@Entity
@Table(name="product")
public class ProductEntity {
	
	private int productID;
	
	@FormParam("productBrands")
	private ProductBrands productBrands;
	
	@FormParam("productName")
	private String productName;
	
	@FormParam("productPrice")
	private double productPrice;
	
	Set<ProductSizeAssignmentEntity> assignedProducts = new HashSet<ProductSizeAssignmentEntity>();
	

	@OneToMany(fetch = FetchType.EAGER,mappedBy="linkedProduct", cascade=CascadeType.ALL)
	@XmlTransient
	public Set<ProductSizeAssignmentEntity> getAssignedProducts() {
		return assignedProducts;
	}

	public void setAssignedProducts(Set<ProductSizeAssignmentEntity> assignedProducts) {
		this.assignedProducts = assignedProducts;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public ProductBrands getProductBrands() {
		return productBrands;
	}

	public void setProductBrands(ProductBrands productBrands) {
		this.productBrands = productBrands;
	}

	
	
	




	
	
	
	
	
	
	
	

}
