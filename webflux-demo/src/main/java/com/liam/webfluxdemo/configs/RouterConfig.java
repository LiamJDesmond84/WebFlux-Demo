package com.liam.webfluxdemo.configs;

import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.liam.webfluxdemo.dtos.InputFailedValidationResponse;
import com.liam.webfluxdemo.exceptions.InputValidationException;

import reactor.core.publisher.Mono;


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
	
	// OPTIONAL
	
	// highLevelRouter - /router/square/15
	// Matching Method 			/square/15
	
	@Bean
	public RouterFunction<ServerResponse> highLevelRouter() {
		
		return RouterFunctions.route()
				
				// If URL contains "router" use this method
				.path("router", this::serverResponseRouterFunction)
				.build();
				
		
	}

//	@Bean			- Does not have to be a Bean & can be private if called RouterFunction Bean
	private RouterFunction<ServerResponse> serverResponseRouterFunction() {
		
		return RouterFunctions.route()
				
//				.GET("square/{input}", (x) -> requestHandler.squareHandler(x))
				.GET("square/{input}", requestHandler::squareHandler)
				.GET("square/{input}/validation", requestHandler::squareHandlerWithValidation)
				
				 // */startes with 1# - Matching only 1 more character OR is 20
				.GET("square/{input}/withPredicates", RequestPredicates.path("*/1?").or(RequestPredicates.path("*/20")),requestHandler::squareHandler)
				.GET("square/{input}", req -> ServerResponse.badRequest().bodyValue("Allowed range is 10 to 20"))
				
				.GET("table/{input}", requestHandler::tableHandler)
				.GET("table/{input}/validation", requestHandler::tableHandlerWithValidation)
				.GET("table/{input}/stream", requestHandler::tableStreamHandler)
				
				.POST("multiple", requestHandler::multiplyHandler)
				.onError(InputValidationException.class, exceptionHandler())
				.build();
		
		
//	@Bean
//	public RouterFunction<ServerResponse> serverResponseRouterFunction() {
//		
//		return RouterFunctions.route()
//				
//				.GET("router/square/{input}", (x) -> requestHandler.squareHandler(x))
////					.GET("router/square/{input}", requestHandler::squareHandler)
//				.GET("router/table/{input}", requestHandler::tableHandler)
//				.GET("router/table/{input}/stream", requestHandler::tableStreamHandler)
//				.POST("router/multiple", requestHandler::multiplyHandler)
//				.GET("router/square/{input}/validation", requestHandler::squareHandlerWithValidation)
//				.onError(InputValidationException.class, exceptionHandler())
//				.build();		
		
		
	}
	
	
	private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler() {
		
		return (err, req) -> {
			
			InputValidationException excep = (InputValidationException) err;
			
			InputFailedValidationResponse response = new InputFailedValidationResponse();
			
			response.setInput(excep.getInput());
			response.setMessage(excep.getMessage());
			response.setErrorCode(excep.getErrorcode());
			
			return ServerResponse.badRequest().bodyValue(response);
			
			

			
			
		};
	}
}
