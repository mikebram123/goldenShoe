package com.and.goldenShoe.customer;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.and.goldenShoe.basket.BasketDAO;
import com.and.goldenShoe.basket.BasketEntity;

@Component
public class CustomerService implements CustomerAPI {
	
	@Autowired
	CustomerDAO cusDAO;
	
	@Autowired
	BasketDAO basDAO;

	public CustomerEntity addCustomer(CustomerEntity newCustomer) throws NoSuchElementException {
		BasketEntity basket = new BasketEntity();
		System.out.println(basket);
		//Checks if user name is already present in database if true throws error
		if (cusDAO.checkUsername(newCustomer.getCustomerUser())!=null) {
			throw new NoSuchElementException("User name is in use");
		}
		basDAO.save(basket);
		cusDAO.save(newCustomer);
		joinBasketAndCustomer(basket.getBasketID(), newCustomer.getCustomerID());
		cusDAO.save(newCustomer);
		return newCustomer;
	}

	public CustomerEntity getCustomerLogin(String user, String password) {
		return cusDAO.findCustomerLogin(user, password);
	}
	
	public CustomerEntity joinBasketAndCustomer(int baskID, int cusID) {
		CustomerEntity cus = cusDAO.findById(cusID).get();
		BasketEntity bask = basDAO.findById(baskID).get();
		
		bask.setAssignedCustomer(cus);
		cus.getCustomerBasket().add(bask);
		
		basDAO.save(bask);
		cusDAO.save(cus);
		
		
		return cus;
		
	}

	public CustomerEntity findCustomerId(int customerID) {
		return cusDAO.findById(customerID).get();
	}
	
	

}
