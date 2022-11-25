package com.liam.webfluxdemo.configs;

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
	
	private int getValue(ServerRequest serverRequest, String key) {
		
		return Integer.parseInt(serverRequest.pathVariable(key));
	}
}
