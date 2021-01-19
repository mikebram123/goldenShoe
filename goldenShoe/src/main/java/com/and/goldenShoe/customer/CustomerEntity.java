package com.and.goldenShoe.customer;

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

import com.and.goldenShoe.basket.BasketEntity;

@Entity
@Table(name="customer")
public class CustomerEntity {
	
	private int customerID;
	
	@FormParam("customerName")
	private String customerName;
	
	@FormParam("customerEmail")
	private String customerEmail;
	
	@FormParam("customerPass")
	private String customerPass;
	
	@FormParam("customerUser")
	private String customerUser;
	
	Set<BasketEntity> customerBasket = new HashSet<BasketEntity>();


	@OneToMany(mappedBy="assignedCustomer", cascade = CascadeType.ALL)
	@XmlTransient
	public Set<BasketEntity> getCustomerBasket() {
		return customerBasket;
	}

	public void setCustomerBasket(Set<BasketEntity> customerBasket) {
		this.customerBasket = customerBasket;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPass() {
		return customerPass;
	}

	public void setCustomerPass(String customerPass) {
		this.customerPass = customerPass;
	}

	public String getCustomerUser() {
		return customerUser;
	}

	public void setCustomerUser(String customerUser) {
		this.customerUser = customerUser;
	}
	
	
	
	

}
