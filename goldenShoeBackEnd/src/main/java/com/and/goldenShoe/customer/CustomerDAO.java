package com.and.goldenShoe.customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource(path = "customer")
public interface CustomerDAO extends CrudRepository<CustomerEntity, Integer>{
	
	@Query("SELECT c FROM CustomerEntity c WHERE LOWER(c.customerUser) = LOWER(:customerUser) AND c.customerPass = :customerPass")
	public CustomerEntity findCustomerLogin(@Param("customerUser") String customerUser, @Param("customerPass") String customerPass);


}
