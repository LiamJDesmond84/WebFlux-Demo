package com.liam.webfluxdemo;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;



public class L07QueryParamsTest extends BaseTest {
	
	
	@Autowired
	private WebClient webClient;
	
	String queryString = "http://localhost:8080/jobs/search?count={count}&page={page}";
	
	
	@Test
	public void queryParamsTest() {
		
		// Alternate builder with query params
		
//		URI uri = UriComponentsBuilder.fromUriString(queryString)
//			.build(10, 20);
		
		Flux<Integer> integerFlux = webClient
			.get()
//			.uri(uri)
			.uri(x -> x.path("http://localhost:8080/jobs/search").query("count={count}&page={page}").build(10, 20))	
			.retrieve()
			.bodyToFlux(Integer.class)
			.doOnNext(System.out::println);
			
		StepVerifier.create(integerFlux)
			.expectNextCount(2)
			.verifyComplete();
		
		
	}

}
