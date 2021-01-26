package com.and.goldenShoe.productSize;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository
public interface SizeDAO extends CrudRepository<SizeEntity, Integer>{
	
	
	//Queries the database by size allowing method to filter by size
	@Query("SELECT s FROM SizeEntity s WHERE s.size = :size")
	public SizeEntity findSize(@Param("size") double size);

}
