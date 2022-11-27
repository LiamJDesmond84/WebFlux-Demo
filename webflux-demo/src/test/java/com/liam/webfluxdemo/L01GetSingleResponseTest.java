package com.liam.webfluxdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.liam.webfluxdemo.dtos.Response;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class L01GetSingleResponseTest extends BaseTest {
	
	@Autowired
	private WebClient webClient;
	
	
	@Test
	public void blockTest() {
		
		
		Response response = webClient
			.get()
			.uri("reactive-math/square/{inputVar}", 5)
			.retrieve()
			.bodyToMono(Response.class) // Mono<Response>
			.block();
		
		System.out.println(response);
		
	}
	
	@Test
	public void stepVerifierTest() {
		
		
		Mono<Response> responseMono = webClient
			.get()
			.uri("reactive-math/square/{inputVar}", 5)
			.retrieve()
			.bodyToMono(Response.class) // Mono<Response>
			.log();
		
		StepVerifier.create(responseMono)
			.expectNextMatches(x -> x.getOutput() == 25)
			.verifyComplete();
		
	}

}
