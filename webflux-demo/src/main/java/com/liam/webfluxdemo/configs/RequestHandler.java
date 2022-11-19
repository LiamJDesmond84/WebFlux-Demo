package com.liam.webfluxdemo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.liam.webfluxdemo.dtos.Response;
import com.liam.webfluxdemo.services.ReactiveMathService;

import reactor.core.publisher.Mono;

@Service
public class RequestHandler {
	
		@Autowired
		private ReactiveMathService reactiveMathService;
		
		
		public Mono<ServerResponse> squareHandler(ServerRequest serverRequest) {
			
			// Accessing the variable
			int input = Integer.valueOf(serverRequest.pathVariable("input"));
			
			// Publisher?
			Mono<Response> responseMono = reactiveMathService.findSquare(input);
			
			return ServerResponse.ok().body(responseMono, Response.class);
		}

}
