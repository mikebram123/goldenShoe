package com.and.goldenShoe.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerDAO extends CrudRepository<CustomerEntity, Integer>{

}
