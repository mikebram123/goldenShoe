package com.and.goldenShoe.basket;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.and.goldenShoe.product.ProductEntity;

@Path("/basket")
public interface BasketAPI {

	@POST
	@Path("/addToCart")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON})
	public ProductEntity addToCart(@FormParam("quantity") int quantity, @FormParam("productID") int productID,
			@FormParam("customerID")int customerID, @FormParam("size") double size);
	
}
