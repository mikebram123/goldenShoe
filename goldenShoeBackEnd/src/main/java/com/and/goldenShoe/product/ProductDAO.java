package com.and.goldenShoe.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "product")
public interface ProductDAO extends CrudRepository<ProductEntity, Integer>{
	
	/*
	 * Queries relating to product declared here that are used to query the data base
	 * 
	 */

	//Allows to filter by brand
	@Query("SELECT p FROM ProductEntity p WHERE p.productBrands = :productBrands")
    public Iterable<ProductEntity> findShoesByBrand (@Param("productBrands") ProductBrands productBrands);
}
