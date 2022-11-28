package com.liam.webfluxdemo;


import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.liam.webfluxdemo.dtos.Response;



import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class L05BadRequestTest extends BaseTest {
	
	@Autowired
	private WebClient webClient;
	
	@Test
	public void badRequestTest() {
		
		
		Mono<Response> responseFlux = webClient
			.get()
			.uri("reactive-math/square/{inputVar}/throw", 5)
			.retrieve()
			.bodyToMono(Response.class) // Mono<Response>
			.doOnNext(x -> System.out.println("Print Statement: " + x))
			.doOnError(err -> System.out.println(err.getMessage()));
//			.doOnNext(System.out::println)
//			.log();
		
		StepVerifier.create(responseFlux)
//			.expectNextCount(1)
//			.verifyComplete();
			.verifyError(WebClientResponseException.BadRequest.class); // From error stack-trace
		
	}

}
