package com.liam.webfluxdemo;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;



public class L07QueryParamsTest extends BaseTest {
	
	
	@Autowired
	private WebClient webClient;
	
	String queryString = "http://localhost:8080/jobs/search?count={count}&page={page}";
	
	
	@Test
	public void queryParamsTest() {
		
		URI uri = UriComponentsBuilder.fromUriString(queryString)
			.build(10, 20);
		
		webClient.get()
			.uri(uri)
			.retrieve()
			.bodyToFlux(Integer.class)
			.doOnNext(System.out::println);
			
		
		
	}

}
