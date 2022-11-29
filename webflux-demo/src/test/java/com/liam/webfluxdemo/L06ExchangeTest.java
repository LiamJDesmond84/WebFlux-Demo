package com.liam.webfluxdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;


import com.liam.webfluxdemo.dtos.InputFailedValidationResponse;
import com.liam.webfluxdemo.dtos.Response;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class L06ExchangeTest extends BaseTest {
	
	@Autowired
	private WebClient webClient;
	
	// .exchange = .retrieve + additional info: http, status code, etc
	@Test
	public void badRequestTest() {
		
		// Will either return a Mono<Response> or MonoInputFailedValidationResponse>
		// Depending on the status code
		
		Mono<Object> responseFlux = webClient
			.get()
			.uri("reactive-math/square/{inputVar}/throw", 5)
			.exchangeToMono(this::exchangebody) // Mono<Response> or MonoInputFailedValidationResponse>
			.doOnNext(x -> System.out.println("Print Statement: " + x))
			.doOnError(err -> System.out.println(err.getMessage()));
//			.doOnNext(System.out::println)
//			.log();
		
		StepVerifier.create(responseFlux)
			.expectNextCount(1)
			.verifyComplete();

		
	}
	
	
	private Mono<Object> exchangebody(ClientResponse clientResp) {
		
		if(clientResp.rawStatusCode() == 400 ) {
			return clientResp.bodyToMono(InputFailedValidationResponse.class);
		}
		else {
			return clientResp.bodyToMono(Response.class);
		}
		
	}

}
