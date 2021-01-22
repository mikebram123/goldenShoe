package com.and.goldenShoe.productBasketAssignment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductBasketAssignmentDAO extends CrudRepository<ProductBasketAssignmentEntity, Integer> {

}
