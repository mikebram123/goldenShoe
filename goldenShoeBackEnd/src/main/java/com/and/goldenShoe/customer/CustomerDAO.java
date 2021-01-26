package com.and.goldenShoe.customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource(path = "customer")
public interface CustomerDAO extends CrudRepository<CustomerEntity, Integer>{
	
	/*
	 * Queries relating to customer declared here that are used to query the data base
	 * 
	 */
	
	//This finds the correct customer record if they exist in the database using user name and password, used when logging in UI
	@Query("SELECT c FROM CustomerEntity c WHERE LOWER(c.customerUser) = LOWER(:customerUser) AND c.customerPass = :customerPass")
	public CustomerEntity findCustomerLogin(@Param("customerUser") String customerUser, @Param("customerPass") String customerPass);
	
	//Ensures that user names are unique 
	@Query("SELECT c FROM CustomerEntity c WHERE LOWER(c.customerUser) = LOWER(:customerUser)")
    public CustomerEntity checkUsername(@Param("customerUser") String user);


}
