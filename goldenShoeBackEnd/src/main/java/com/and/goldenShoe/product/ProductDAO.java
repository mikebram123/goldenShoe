package com.and.goldenShoe.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "product")
public interface ProductDAO extends CrudRepository<ProductEntity, Integer>{

	@Query("SELECT p FROM ProductEntity p WHERE p.productBrands = :productBrands")
    public Iterable<ProductEntity> findShoesByBrand (@Param("productBrands") ProductBrands productBrands);
}
