package com.and.goldenShoe.basket;

import java.util.HashSet;
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

import com.and.goldenShoe.customer.CustomerEntity;
import com.and.goldenShoe.productBasketAssignment.ProductBasketAssignmentEntity;

/**
 * Contains data, relationships and methods needed to create a basket entity
 * 
 * @author Michael Bramhall
 */

@Entity
@Table(name="basket")
public class BasketEntity {
	
	//Primary Key of basket
	private int basketID;
	
	//Total price of all products contained in basket
	@FormParam("totalValue")
	private double totalValue;
	
	//Every basket has assignedCustomer linked to it
	private CustomerEntity assignedCustomer;
	
	//Every basket can have multiple products in it
	private Set<ProductBasketAssignmentEntity> basketProducts = new HashSet<ProductBasketAssignmentEntity>();
	
	//Is the currentBasket in use or not
	private boolean currentBasket;
	
	/**
	 * Constructor initialises the currentBasket to true 
	 * only currentBasket==true is shown in UI
	 */
	public BasketEntity() {
		this.currentBasket = true;
	}
	
	//Relationship between product and basket
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "linkedBasket", cascade=CascadeType.ALL)
	@XmlTransient
	public Set<ProductBasketAssignmentEntity> getBasketProducts() {
		return basketProducts;
	}

	public void setBasketProducts(Set<ProductBasketAssignmentEntity> basketProducts) {
		this.basketProducts = basketProducts;
	}

	//Relationship between basket and customer
	@ManyToOne
	@JoinColumn(name="fk_customerID")
	public CustomerEntity getAssignedCustomer() {
		return assignedCustomer;
	}

	public void setAssignedCustomer(CustomerEntity assignedCustomer) {
		this.assignedCustomer = assignedCustomer;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getBasketID() {
		return basketID;
	}

	public void setBasketID(int basketID) {
		this.basketID = basketID;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public boolean isCurrentBasket() {
		return currentBasket;
	}

	public void setCurrentBasket(boolean currentBasket) {
		this.currentBasket = currentBasket;
	}
	
	

	
	
	

}
