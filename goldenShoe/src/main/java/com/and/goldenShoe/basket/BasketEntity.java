package com.and.goldenShoe.basket;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

@Entity
@Table(name="basket")
public class BasketEntity {
	
	private int basketID;
	
	@FormParam("totalValue")
	private double totalValue;
	
	private CustomerEntity assignedCustomer;
	
	private Set<ProductBasketAssignmentEntity> basketProducts = new HashSet<ProductBasketAssignmentEntity>();
	
	
	@OneToMany(mappedBy= "linkedBasket", cascade=CascadeType.ALL)
	@XmlTransient
	public Set<ProductBasketAssignmentEntity> getBasketProducts() {
		return basketProducts;
	}

	public void setBasketProducts(Set<ProductBasketAssignmentEntity> basketProducts) {
		this.basketProducts = basketProducts;
	}

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

	
	
	

}
