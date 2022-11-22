package com.liam.webfluxdemo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import org.springframework.web.reactive.function.server.ServerResponse;


// Instead of Controller
// RouterConfig -> RouterHandler -> ReactiveMathService
@Configuration
public class RouterConfig {
	
	// FUNCTIONAL ENDPOINTS - Alternative method to using the Controller
	
	// https://www.baeldung.com/spring-5-functional-web
	
	// First, we need to create routes using RouterFunction to publish and consume our reactive streams of Employees.

	// Routes are registered as Spring beans and can be created inside any configuration class.
	
	@Autowired
	private RequestHandler requestHandler;

	@Bean
	public RouterFunction<ServerResponse> serverResponseRouterFunction() {
		
		return RouterFunctions.route()
				.GET("router/square/{input}", (x) -> requestHandler.squareHandler(x))
//				.GET("router/square/{input}", requestHandler::squareHandler)
				.GET("router/table/{input}", requestHandler::tableHandler)
				.GET("router/table/{input}/stream", requestHandler::tableStreamHandler)
				.POST("router/multiple/{input}/stream", requestHandler::multiplyHandler)
				.build();
		
		
	}
}
