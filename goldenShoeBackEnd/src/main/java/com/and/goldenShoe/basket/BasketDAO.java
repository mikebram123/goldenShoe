package com.and.goldenShoe.basket;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BasketDAO extends CrudRepository<BasketEntity, Integer>{
	

}
