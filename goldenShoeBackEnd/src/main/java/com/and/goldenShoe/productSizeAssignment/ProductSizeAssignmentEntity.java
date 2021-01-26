package com.and.goldenShoe.productSizeAssignment;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlTransient;

import com.and.goldenShoe.product.ProductEntity;
import com.and.goldenShoe.productBasketAssignment.ProductBasketAssignmentEntity;
import com.and.goldenShoe.productSize.SizeEntity;

/**
 * Contains data, relationships and methods needed to create a ProductSizeAssingment entity
 * 
 * @author Michael Bramhall
 */

@Entity
@Table(name="product_size_assignment")
public class ProductSizeAssignmentEntity {
	
	private int product_size_assignmentID;
	
	//Each assignment has a specific product linked to a specific size
	private ProductEntity linkedProduct;
	
	private SizeEntity linkedSize;
	
	//Quantity of that specific product in a specific size
	@FormParam("quantity")
	private int quantity;
	
	private Set<ProductBasketAssignmentEntity> linkedBaskets;
	
	
	
	//Relationship between ProductSizeAssignment and basket
	@OneToMany(fetch = FetchType.EAGER, mappedBy="linkedSizes", cascade = CascadeType.ALL)
	@XmlTransient
	public Set<ProductBasketAssignmentEntity> getLinkedBaskets() {
		return linkedBaskets;
	}

	public void setLinkedBaskets(Set<ProductBasketAssignmentEntity> linkedBaskets) {
		this.linkedBaskets = linkedBaskets;
	}

	@ManyToOne
	@JoinColumn(name= "fk_productID")
	public ProductEntity getLinkedProduct() {
		return linkedProduct;
	}

	public void setLinkedProduct(ProductEntity linkedProduct) {
		this.linkedProduct = linkedProduct;
	}

	@ManyToOne
	@JoinColumn(name= "fk_sizeID")
	public SizeEntity getLinkedSize() {
		return linkedSize;
	}

	public void setLinkedSize(SizeEntity linkedSize) {
		this.linkedSize = linkedSize;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getProduct_size_assignmentID() {
		return product_size_assignmentID;
	}

	public void setProduct_size_assignmentID(int product_size_assignmentID) {
		this.product_size_assignmentID = product_size_assignmentID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
