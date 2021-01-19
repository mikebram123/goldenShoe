package com.and.goldenShoe.customer;

import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/customer")
public interface CustomerAPI {
	
    // POST http://localhost:8080/goldenshoe/customer/register
    @POST
    @Path("/register")
    @Produces({MediaType.APPLICATION_JSON})
    public CustomerEntity addCustomer(@BeanParam CustomerEntity newCustomer);

}
