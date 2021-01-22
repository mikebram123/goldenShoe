package com.and.goldenShoe.productSizeAssignment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeAssignmentDAO extends CrudRepository<ProductSizeAssignmentEntity, Integer>{

}
