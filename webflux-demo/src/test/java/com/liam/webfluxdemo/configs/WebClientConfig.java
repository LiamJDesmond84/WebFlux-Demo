package com.liam.webfluxdemo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

	@Bean
	public WebClient webClient() {
		
		return WebClient.builder()
				.baseUrl("http://localhost:8080")
				.defaultHeaders(h -> h.setBasicAuth("username", "password"))
				.build();
	}
	
	
	private Mono<ClientResponse> sessionToken(ClientRequest request, ExchangeFunction exFunc) {
		
		System.out.println("Generating Session");
		
		ClientRequest clientRequest = ClientRequest.from(request)
			.headers(h -> h.setBearerAuth("some-long-jwt"))
			.build();
		
		return exFunc.exchange(clientRequest);
		
	}
}
