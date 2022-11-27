package com.liam.webfluxdemo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;


import com.liam.webfluxdemo.dtos.MultiplyRequestDto;
import com.liam.webfluxdemo.dtos.Response;
import com.liam.webfluxdemo.exceptions.InputValidationException;
import com.liam.webfluxdemo.services.ReactiveMathService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class RequestHandler {
	
		@Autowired
		private ReactiveMathService reactiveMathService;
		
		
		public Mono<ServerResponse> squareHandler(ServerRequest serverRequest) {
			
			// Accessing the variable
			int input = Integer.parseInt(serverRequest.pathVariable("input"));
			
			// Returning this Publisher Interface(Mono, Flux)<Response>
			Mono<Response> responseMono = reactiveMathService.findSquare(input);
			
			return ServerResponse.ok().body(responseMono, Response.class);
			// Returning Reponse
			
			
//			Mono<Mono<Response>> parallelMono = Mono.just(responseMono).subscribeOn(Schedulers.parallel()).map(x -> {
//				log.info("Thread 1: {}", x);
//				return x;
//				}).log();
//		
//		
//			Mono<Mono<Response>> parallelMono2 = Mono.just(responseMono).subscribeOn(Schedulers.parallel()).map(x -> {
//				log.info("Thread 2: {}", x);
//				return x;
//			}).log();
//		
//		return ServerResponse.ok().body(parallelMono.mergeWith(parallelMono2), Response.class);
													
		}
		
		
		// ServerResponse is an object that CONTAINS Flux & Mono - <Response> - Doesn't have to return 1:1
		
		public Mono<ServerResponse> tableHandler(ServerRequest serverRequest) {
			
			// Accessing the variable
			int input = Integer.parseInt(serverRequest.pathVariable("input"));
			
			// Returning this Publisher Interface(Mono, Flux)<Response>
			Flux<Response> responseFlux = reactiveMathService.multiplicationTable(input);
														// Returning Reponse
			return ServerResponse.ok().body(responseFlux, Response.class);
		}
		
		
		
		public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest) {
			
			// Accessing the variable
			int input = Integer.parseInt(serverRequest.pathVariable("input"));
			
			// Returning this Publisher Interface(Mono, Flux)<Response>
			Flux<Response> responseFlux = reactiveMathService.multiplicationTable(input);
														// Returning Reponse
			return ServerResponse.ok()
					.contentType(MediaType.TEXT_EVENT_STREAM)
					.body(responseFlux, Response.class);
		}
		
		
		public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest) {
			
			// Accessing the variable
			Mono<MultiplyRequestDto> requestDTOMono = serverRequest.bodyToMono(MultiplyRequestDto.class);
			
			Mono<Response> responseMono = this.reactiveMathService.multiply(requestDTOMono);

			return ServerResponse.ok()
					.contentType(MediaType.TEXT_EVENT_STREAM)
					.body(responseMono, Response.class);
		}
		
		
		// WITH VALIDATION
		
		public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest) {
			
			// Accessing the variable
			int input = Integer.parseInt(serverRequest.pathVariable("input"));
			
			if(input < 10 || input > 20) {
				
				return Mono.error(new InputValidationException(input));	
				
			}
			// Returning this Publisher Interface(Mono, Flux)<Response>
			Mono<Response> responseMono = reactiveMathService.findSquare(input);
														// Returning Reponse
			return ServerResponse.ok().body(responseMono, Response.class);
		}
		
		// ServerResponse is an object that CONTAINS Flux & Mono - <Response> - Doesn't have to return 1:1
		
		public Mono<ServerResponse> tableHandlerWithValidation(ServerRequest serverRequest) {
			
			// Accessing the variable
			int input = Integer.parseInt(serverRequest.pathVariable("input"));
			
			if(input < 10 || input > 20) {
				
				return Mono.error(new InputValidationException(input));	
				
			}
			
			// Returning this Publisher Interface(Mono, Flux)<Response>
			Flux<Response> responseFlux = reactiveMathService.multiplicationTable(input);
														// Returning Reponse
			return ServerResponse.ok().body(responseFlux, Response.class);
		}

}
