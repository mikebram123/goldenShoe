package com.and.goldenShoe.basket;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.and.goldenShoe.product.ProductEntity;
import com.and.goldenShoe.productBasketAssignment.ProductBasketAssignmentEntity;

@Path("/basket")
public interface BasketAPI {

	@POST
	@Path("/addToCart")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON})
	public ProductEntity addToCart(@FormParam("quantity") int quantity, @FormParam("productID") int productID,
			@FormParam("customerID")int customerID, @FormParam("size") double size);
	
	@POST
	@Path("/fetchCurrentBasket")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON})
	public BasketEntity findBasketByCustomerID(@FormParam("customerID") int customerID);
	
	@POST
	@Path("/fetchBasketProducts")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON})
	public Set<ProductBasketAssignmentEntity> fetchBasketProducts(@FormParam("basketID") int basketID);
	
	@POST
	@Path("/checkout")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON})
	public BasketEntity checkout(@FormParam("basketID") int basketID);
	
	
	
	
}
