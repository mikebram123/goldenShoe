package com.and.goldenShoe.basket;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.and.goldenShoe.customer.CustomerDAO;
import com.and.goldenShoe.customer.CustomerEntity;
import com.and.goldenShoe.customer.CustomerService;
import com.and.goldenShoe.product.ProductDAO;
import com.and.goldenShoe.product.ProductEntity;
import com.and.goldenShoe.productBasketAssignment.ProductBasketAssignmentDAO;
import com.and.goldenShoe.productBasketAssignment.ProductBasketAssignmentEntity;
import com.and.goldenShoe.productSizeAssignment.ProductSizeAssignmentDAO;
import com.and.goldenShoe.productSizeAssignment.ProductSizeAssignmentEntity;

@Component
public class BasketService implements BasketAPI{
	
	@Autowired
	BasketDAO basDAO;
	
	@Autowired
	ProductDAO proDAO;
	
	@Autowired
	ProductBasketAssignmentDAO probasDAO;
	
	@Autowired
	ProductSizeAssignmentDAO proSizeDAO;
	
	@Autowired
	CustomerDAO cusDAO;
	
	@Autowired
	CustomerService cusServ;
	
	@Transactional
	public ProductEntity addToCart(int quantity, int productID, int customerID, double size) throws IllegalArgumentException {
		ProductEntity product = proDAO.findById(productID).get();
		ProductBasketAssignmentEntity probas = new ProductBasketAssignmentEntity();
		Set<ProductSizeAssignmentEntity> asProds = product.getAssignedProducts();
		System.out.println(asProds.size());
		for (ProductSizeAssignmentEntity prodSize : asProds) {
			if(prodSize.getLinkedSize().getSize() != size) {
				continue;
			}
			if (quantity > prodSize.getQuantity()) {
				throw new IllegalArgumentException();
			}
			probas.setQuantityOrdered(quantity);
			probasDAO.save(probas);
			prodSize.setQuantity(prodSize.getQuantity()-quantity);
			proSizeDAO.save(prodSize);
			joinProductToAssignedProduct(productID, probas.getProduct_basket_assignmentID());
			BasketEntity basket = findBasketByCustomerID(customerID);
			basket.setTotalValue(basket.getTotalValue()+(product.getProductPrice()*quantity));
			basDAO.save(basket);
			joinBasketToAssignedProduct(basket.getBasketID(), probas.getProduct_basket_assignmentID());
			
			break;
		}
		
		return product;
	}
	
	@Transactional
	public BasketEntity findBasketByCustomerID(int customerID) {
		CustomerEntity cust = cusDAO.findById(customerID).get();
		Set<BasketEntity> basks = cust.getCustomerBasket();
		BasketEntity currentBasket = null;
		for (BasketEntity basket : basks) {
			if(basket.isCurrentBasket()) {
				currentBasket = basket;
				break;		
			}
		}
		if (currentBasket == null) {
			currentBasket = new BasketEntity();
			basDAO.save(currentBasket);
			cusServ.joinBasketAndCustomer(currentBasket.getBasketID(), customerID);
			
		}
		
		return currentBasket;
	}
	
	
	@Transactional
	public BasketEntity joinBasketToAssignedProduct(int basketID, int AssignedID) {
		BasketEntity basket = basDAO.findById(basketID).get();
		ProductBasketAssignmentEntity proBas = probasDAO.findById(AssignedID).get();
		
		basket.getBasketProducts().add(proBas);
		proBas.setLinkedBasket(basket);
		
		basDAO.save(basket);
		probasDAO.save(proBas);
		
		return basket;
	}
	
	@Transactional
	public ProductEntity joinProductToAssignedProduct (int productID, int assignedID) {
		ProductEntity product = proDAO.findById(productID).get();
		ProductBasketAssignmentEntity proBas = probasDAO.findById(assignedID).get();
		
		product.getAssignedBasketProducts().add(proBas);
		proBas.setLinkedProducts(product);
		
		proDAO.save(product);
		probasDAO.save(proBas);
		
		return product;
	}

}
