package com.and.goldenShoe.apis;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component //declare it as spring component
@ApplicationPath("/goldenshoe/")
public class APIConfig extends ResourceConfig {

	
	public APIConfig() {
		register(CORSFilter.class);
	}
}
