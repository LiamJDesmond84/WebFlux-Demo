package com.liam.webfluxdemo;

import java.net.URI;
import java.util.Map;

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
		
		//1: Alternate builder with query params
		
//		URI uri = UriComponentsBuilder.fromUriString(queryString)
//			.build(10, 20);
		
		
		//2: Alternate for adding queryParams
		Map<String, Integer> map = Map.of(
				"count", 10,
				"page", 20
			);
		
		Flux<Integer> integerFlux = webClient
			.get()
//			.uri(uri)
//			.uri(x -> x.path("/jobs/search").query("count={count}&page={page}").build(10, 20))
			.uri(x -> x.path("/jobs/search").query("count={count}&page={page}").build(map))
			.retrieve()
			.bodyToFlux(Integer.class)
			.doOnNext(System.out::println);
			
		StepVerifier.create(integerFlux)
			.expectNextCount(2)
			.verifyComplete();
		
		
	}

}
