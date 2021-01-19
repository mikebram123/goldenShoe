package com.and.goldenShoe.productSize;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SizeDAO extends CrudRepository<SizeEntity, Integer>{
	
	

}
