package com.and.goldenShoe.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlTransient;


import com.and.goldenShoe.productSizeAssignment.ProductSizeAssignmentEntity;

/**
 * Contains data, relationships and methods needed to create a product entity
 * 
 * @author Michael Bramhall
 */

@Entity
@Table(name="product")
public class ProductEntity {
	
	//Required fields to create a product entity
	private int productID;
	
	@FormParam("productBrands")
	private ProductBrands productBrands;
	
	@FormParam("productName")
	private String productName;
	
	@FormParam("productPrice")
	private double productPrice;
	
	@FormParam("productColour")
	private ProductColour productColour;
	
	@FormParam("productFit")
	private ProductFit productFit;
	
	@FormParam("productStyle")
	private ProductStyle productStyle;
	
	
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

	@Enumerated(EnumType.STRING)
	public ProductColour getProductColour() {
		return productColour;
	}

	public void setProductColour(ProductColour productColour) {
		this.productColour = productColour;
	}

	@Enumerated(EnumType.STRING)
	public ProductFit getProductFit() {
		return productFit;
	}

	public void setProductFit(ProductFit productFit) {
		this.productFit = productFit;
	}

	@Enumerated(EnumType.STRING)
	public ProductStyle getProductStyle() {
		return productStyle;
	}

	public void setProductStyle(ProductStyle productStyle) {
		this.productStyle = productStyle;
	}

	
	
	
	




	
	
	
	
	
	
	
	

}
