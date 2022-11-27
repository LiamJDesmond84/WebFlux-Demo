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
	public void stepVerifierFluxTest() {
		
		
		Flux<Response> responseFlux = webClient
			.get()
			.uri("reactive-math/table/{inputVar}", 5)
			.retrieve()
			.bodyToFlux(Response.class) // Mono<Response>
			.doOnNext(x -> System.out.println("Print Statement: " + x))
//			.doOnNext(System.out::println)
			.log();
		
		StepVerifier.create(responseFlux)
			.expectNextCount(10)
			.verifyComplete();
		
	}

}
