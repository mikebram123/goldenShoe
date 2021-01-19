package com.and.goldenShoe.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerService implements CustomerAPI {
	
	CustomerDAO customerDAO;

	public CustomerEntity addCustomer(CustomerEntity newCustomer) {
		customerDAO.save(newCustomer);
		return newCustomer;
	}

}
