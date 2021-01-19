package com.and.goldenShoe.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlTransient;

import com.and.goldenShoe.productBasketAssignment.ProductBasketAssignmentEntity;
import com.and.goldenShoe.productSizeAssignment.ProductSizeAssignmentEntity;

@Entity
@Table(name="product")
public class ProductEntity {
	
	private int productID;
	
	@FormParam("productName")
	private String productName;
	
	@FormParam("productPrice")
	private String productPrice;
	
	Set<ProductSizeAssignmentEntity> assignedProducts = new HashSet<ProductSizeAssignmentEntity>();
	
	Set<ProductBasketAssignmentEntity> assignedBasketProducts = new HashSet<ProductBasketAssignmentEntity>();
	
	
	

	@OneToMany(mappedBy="linkedProducts", cascade = CascadeType.ALL)
	@XmlTransient
	public Set<ProductBasketAssignmentEntity> getAssignedBasketProducts() {
		return assignedBasketProducts;
	}

	public void setAssignedBasketProducts(Set<ProductBasketAssignmentEntity> assignedBasketProducts) {
		this.assignedBasketProducts = assignedBasketProducts;
	}

	@OneToMany(mappedBy="linkedProduct", cascade=CascadeType.ALL)
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

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productID;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((productPrice == null) ? 0 : productPrice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductEntity other = (ProductEntity) obj;
		if (productID != other.productID)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (productPrice == null) {
			if (other.productPrice != null)
				return false;
		} else if (!productPrice.equals(other.productPrice))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	

}
