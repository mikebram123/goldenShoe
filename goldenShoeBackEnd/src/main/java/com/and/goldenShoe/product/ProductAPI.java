package com.and.goldenShoe.product;

import java.util.Set;

import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/product")
public interface ProductAPI {
	
    // POST http://localhost:8080/goldenshoe/product/register
    @POST
    @Path("/register")
    @Produces({MediaType.APPLICATION_JSON})
    public ProductEntity addProduct(@BeanParam ProductEntity newProduct, @FormParam("size") double size, @FormParam("quantity") int quantity);

    @GET
    @Path("/listAll")
    @Produces({MediaType.APPLICATION_JSON})
    public Set<ProductEntity> listAllProducts();
    
    @POST
    @Path("/findBrand")
    @Produces({MediaType.APPLICATION_JSON})
    public Set<ProductEntity> findByBrand(@FormParam("brand") ProductBrands brand);
    
    @POST
    @Path("/uploadNewSizes")
    @Produces({MediaType.APPLICATION_JSON})
    public ProductEntity UpdateSizes(@FormParam("productID") int productID, @FormParam("size") double size, @FormParam("quantity") int quantity);
    
}
