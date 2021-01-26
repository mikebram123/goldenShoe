package com.and.goldenShoe.productBasketAssignment;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.ws.rs.FormParam;


import com.and.goldenShoe.basket.BasketEntity;
import com.and.goldenShoe.productSizeAssignment.ProductSizeAssignmentEntity;

@Entity
@Table(name="product_basket_assignment")
public class ProductBasketAssignmentEntity {
	
	@FormParam("product_basket_assignmentID")
	private int product_basket_assignmentID;
	
	@FormParam("quantityOrdered")
	private int quantityOrdered;
	
	private BasketEntity linkedBasket;
	
	private ProductSizeAssignmentEntity linkedSizes;
	
	
	
	@ManyToOne
	@JoinColumn(name="fk_productSizeAssignmentID")
//	@XmlTransient
	public ProductSizeAssignmentEntity getLinkedSizes() {
		return linkedSizes;
	}

	public void setLinkedSizes(ProductSizeAssignmentEntity linkedSizes) {
		this.linkedSizes = linkedSizes;
	}

	@ManyToOne
	@JoinColumn(name="fk_BasketID")
//	@XmlTransient
	public BasketEntity getLinkedBasket() {
		return linkedBasket;
	}

	public void setLinkedBasket(BasketEntity linkedBasket) {
		this.linkedBasket = linkedBasket;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getProduct_basket_assignmentID() {
		return product_basket_assignmentID;
	}

	public void setProduct_basket_assignmentID(int product_basket_assignmentID) {
		this.product_basket_assignmentID = product_basket_assignmentID;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}
	
	

}
