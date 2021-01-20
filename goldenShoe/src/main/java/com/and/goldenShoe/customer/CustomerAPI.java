package com.and.goldenShoe.customer;

import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;




@Path("/customer")
public interface CustomerAPI {
	
    // POST http://localhost:8080/goldenshoe/customer/register
    @POST
    @Path("/register")
    @Produces({MediaType.APPLICATION_JSON})
    public CustomerEntity addCustomer(@BeanParam CustomerEntity newCustomer);
    
    // POST http://localhost:8080/goldenshoe/customer/login
    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
	public CustomerEntity getCustomerLogin(@FormParam("customerUser") String user, @FormParam("customerPass") String password);
    
    
    //@GET http://localhost:8080/goldenshoe/customer/find/{customerID}
	@GET 
	@Path("/find/{customerID}") // url with parameter format
	@Produces({MediaType.APPLICATION_JSON})
	public CustomerEntity findCustomerId(@PathParam("customerID") int customerID);

}
