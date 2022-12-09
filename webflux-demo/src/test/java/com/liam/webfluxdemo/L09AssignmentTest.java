package com.liam.webfluxdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class L09AssignmentTest extends BaseTest {
	
	private static final String FORMAT = "%d %s %d = %s";
	
	private static final int A = 10;
	
	
	
	@Autowired
	private WebClient webClient;

	
	@Test
	public void Test() {
		
		
	}
	
	private Mono<String> send(int b, String oper) {
		
		return webClient
		.get()
		.uri("calculator/{a}/{b}", A, b)
		.headers(h -> h.set("OP", oper))
		.retrieve()
		.bodyToMono(String.class)
		.map(v -> String.format(FORMAT, A, oper, b, v));
	}
}
