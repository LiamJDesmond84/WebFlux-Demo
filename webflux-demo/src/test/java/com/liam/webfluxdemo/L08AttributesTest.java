package com.liam.webfluxdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.liam.webfluxdemo.dtos.MultiplyRequestDto;
import com.liam.webfluxdemo.dtos.Response;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class L08AttributesTest extends BaseTest {
	
	
	@Autowired
	private WebClient webClient;
	
	String queryString = "http://localhost:8080/jobs/search?count={count}&page={page}";
	
	// Here is what we are sending
	@Test
	public void headersTestWithAuth() {
		
		Mono<Response> responseMono = webClient
			.post()
			.uri("reactive-math/multiply")
			.bodyValue(buildRequestDto(5, 2))
//			.attribute("auth", "sdfsfd")
			.retrieve()
			.bodyToMono(Response.class)
			.doOnNext(System.out::println);
		
		StepVerifier.create(responseMono)
			.expectNextCount(1)
			.verifyComplete();
		

		
		
	}
	
	
	private MultiplyRequestDto buildRequestDto(int a, int b) {
		
		MultiplyRequestDto dto = new MultiplyRequestDto();
		
		dto.setFirst(a);
		dto.setSecond(b);
		
		return dto;
	}

}
