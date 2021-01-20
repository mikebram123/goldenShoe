package com.and.goldenShoe.product;

import org.springframework.beans.factory.annotation.Autowired;

import com.and.goldenShoe.productSize.SizeDAO;
import com.and.goldenShoe.productSize.SizeEntity;
import com.and.goldenShoe.productSizeAssignment.ProductSizeAssignmentDAO;
import com.and.goldenShoe.productSizeAssignment.ProductSizeAssignmentEntity;

public class ProductService implements ProductAPI {
	
	@Autowired
	ProductDAO proDAO;
	
	@Autowired
	ProductSizeAssignmentDAO assDAO;
	
	@Autowired
	SizeDAO sizeDAO;
	

	
	public ProductEntity joinProductAssigned(int productID, int assignedID) {
		ProductEntity product = proDAO.findById(productID).get();
		ProductSizeAssignmentEntity assigned = assDAO.findById(assignedID).get();
		
		assigned.setLinkedProduct(product);
		product.getAssignedProducts().add(assigned);
		
		assDAO.save(assigned);
		proDAO.save(product);
		
		return product;
		
	}
	
	public SizeEntity joinSizeAssigned(int sizeID, int assignedID) {
		SizeEntity size = sizeDAO.findById(sizeID).get();
		ProductSizeAssignmentEntity assigned = assDAO.findById(assignedID).get();
		
		assigned.setLinkedSize(size);
		size.getAssignedSize().add(assigned);
		
		assDAO.save(assigned);
		sizeDAO.save(size);
		
		return size;	
	}

	public ProductEntity addProduct(ProductEntity newProduct, double size, int quantity) {
		SizeEntity sizeE = sizeDAO.findSize(size);
		System.out.println(sizeE);
		ProductSizeAssignmentEntity prodS = new ProductSizeAssignmentEntity();
		System.out.println(prodS);
		
		prodS.setQuantity(quantity);
		proDAO.save(newProduct);
		assDAO.save(prodS);
		
		joinProductAssigned(newProduct.getProductID(), prodS.getProduct_size_assignmentID());
		joinSizeAssigned(sizeE.getSizeID(), prodS.getProduct_size_assignmentID());
		
		
		return newProduct;
	}

	

}
