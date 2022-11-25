package com.liam.webfluxdemo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CalculatorRouterConfig {

	
	@Autowired
	CalculatorHandler calculatorHandler;
	
	@Bean
	public RouterFunction<ServerResponse> highLevelCalcRouter() {
		
		return RouterFunctions.route()
				.path("calculator", this::serverResponseCalcRouterFunction)
				.build();
	}

	
	private RouterFunction<ServerResponse> serverResponseCalcRouterFunction() {
		
		return RouterFunctions.route()
			.GET("{a}/{b}", isOperation("+"), calculatorHandler::additionHandler)
			.GET("{a}/{b}", isOperation("-"), calculatorHandler::subtractionHandler)
			.GET("{a}/{b}", isOperation("*"), calculatorHandler::multiplicationHandler)
			.GET("{a}/{b}", isOperation("/"), calculatorHandler::divisionHandler)
			.GET("{a}/{b}", req -> ServerResponse.badRequest().bodyValue("OP header must include +, -, * or /"))
			.build();
			
	}
	
	private RequestPredicate isOperation(String operation) {
		
		return RequestPredicates.headers(header -> operation.equals(header.asHttpHeaders()
													.toSingleValueMap().get("OP")));
		
	}
	
	
	
//	public RouterFunction<ServerResponse> serverResponseCalcRouterFunction() {
//		
//		return RouterFunctions.route()
//			.GET("add/{a}/{b}", calculatorHandler::additionHandler)
//			.GET("sub/{a}/{b}", calculatorHandler::subtractionHandler)
//			.GET("mul/{a}/{b}", calculatorHandler::multiplicationHandler)
//			.GET("div/{a}/{b}", calculatorHandler::divisionHandler)
//			.build();
//			
//	}
	
}
