package com.and.goldenShoe.apis;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.and.goldenShoe.basket.BasketService;
import com.and.goldenShoe.customer.CustomerService;
import com.and.goldenShoe.product.ProductService;

@Component
@ApplicationPath("/goldenshoe/")
public class APIConfig extends ResourceConfig {

	//Each service class registered to enable services as API
	public APIConfig() {
		register(CORSFilter.class);
		register(CustomerService.class);
		register(ProductService.class);
		register(BasketService.class);
	}
}
