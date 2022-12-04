package com.liam.webfluxdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.liam.webfluxdemo.dtos.MultiplyRequestDto;
import com.liam.webfluxdemo.dtos.Response;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class L04HeadersTest extends BaseTest{
	
	@Autowired
	private WebClient webClient;
	
	
	@Test
	public void headersTest() {
		
		Mono<Response> responseMono = webClient
			.post()
			.uri("reactive-math/multiply")
			.headers(h -> h.set("someKey", "someValue"))
//			.headers(h -> h.setBasicAuth("username", "password"))
			.header("someKey2", "someValue2")
			.bodyValue(buildRequestDto(5, 2))
			.retrieve()
			.bodyToMono(Response.class)
			.doOnNext(System.out::println);
		
		StepVerifier.create(responseMono) // Session Token Generator invoked individually
			.expectNextCount(1)
			.verifyComplete();
		
		StepVerifier.create(responseMono) // Session Token Generator invoked individually
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
