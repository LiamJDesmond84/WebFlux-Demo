package com.liam.webfluxdemo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CalculatorRouterConfig {

	
	@Autowired
	CalculatorHandler calculatorHandler;
	
	
//	public RouterFunction<ServerResponse> highLevelCalcRouter {
//		
//		return RouterFunctions.route()
//				.path
//	

	
	public RouterFunction<ServerResponse> serverResponseCalcRouterFunction() {
		
		return RouterFunctions.route()
			.GET("add/{a}/{b}", calculatorHandler::additionHandler)
			.GET("sub/{a}/{b}", calculatorHandler::subtractionHandler)
			.GET("mul/{a}/{b}", calculatorHandler::multiplicationHandler)
			.GET("div/{a}/{b}", calculatorHandler::divisionHandler)
			.build();
			
	}
	
}
