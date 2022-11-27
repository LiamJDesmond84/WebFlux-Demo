package com.liam.webfluxdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.liam.webfluxdemo.dtos.Response;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class L02GetMultiResponseTest extends BaseTest {
	
	@Autowired
	private WebClient webClient;
	
	
	@Test
	public void stepVerifierTest2() {
		
		
		Flux<Response> responseMono = webClient
			.get()
			.uri("reactive-math/square/{inputVar}", 5)
			.retrieve()
			.bodyToFlux(Response.class) // Mono<Response>
			.log();
		
		StepVerifier.create(responseMono)
			.expectNextMatches(x -> x.getOutput() == 25)
			.verifyComplete();
		
	}

}
