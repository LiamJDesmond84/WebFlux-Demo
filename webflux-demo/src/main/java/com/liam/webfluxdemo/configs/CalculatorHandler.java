package com.liam.webfluxdemo.configs;



import java.util.function.BiFunction;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Service
public class CalculatorHandler {

	// Creating multiple handlers intentionally
	// Calculator/{a}/{b}
	public Mono<ServerResponse> additionHandler(ServerRequest serverRequest) {
		
		int a = getValue(serverRequest, "a");
		int b = getValue(serverRequest, "b");
		
		return ServerResponse.ok().bodyValue(a + b);
	}
	
	
	// Using intProcess below
	public Mono<ServerResponse> subtractionHandler(ServerRequest request) {
		
		return intProcess(request, (a, b) -> ServerResponse.ok().bodyValue(a - b));
	}
	
	// Using intProcess below
	public Mono<ServerResponse> multiplicationHandler(ServerRequest request) {
		
		return intProcess(request, (a, b) -> ServerResponse.ok().bodyValue(a * b));
	}
	
	// Using intProcess below
	public Mono<ServerResponse> divisionHandler(ServerRequest request) {
		
		return intProcess(request, (a, b) -> ServerResponse.ok().bodyValue(a / b));
	}
	
	
	private Mono<ServerResponse> intProcess(ServerRequest request, BiFunction<Integer, Integer, Mono<ServerResponse>> operationLogic) {
		
		int a = getValue(request, "a");
		int b = getValue(request, "b");
		
		return operationLogic.apply(a, b);
		
		
	}
	
	
	private int getValue(ServerRequest request, String key) {
		
		return Integer.parseInt(request.pathVariable(key));
	}
}
