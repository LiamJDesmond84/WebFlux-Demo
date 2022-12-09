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
//				.filter((clientRequestVar, exchangeFunctionVar) -> sessionToken(clientRequestVar, exchangeFunctionVar)) // Same as below
//				.filter(this::sessionToken)
				.filter(this::sessionAuthToken)
				.build();
	}
	
	
	private Mono<ClientResponse> sessionToken(ClientRequest request, ExchangeFunction exFunc) {
		
		System.out.println("Generating Session Token");
		
		ClientRequest clientRequest = ClientRequest.from(request)
			.headers(h -> h.setBearerAuth("some-long-jwt"))
			.build();
		
		return exFunc.exchange(clientRequest);
		
	}
	
	private Mono<ClientResponse> sessionAuthToken(ClientRequest request, ExchangeFunction exFunc) {
		
		// Key: auth -> basic or OAuth
		ClientRequest clientRequest = request.attribute("auth")
			.map(val -> val.equals("basic") ? withBasicAuthentication(request) : withOAuth(request))
			.orElse(request);
		
		return exFunc.exchange(clientRequest);
		
	}
	
	
	private ClientRequest withBasicAuthentication(ClientRequest clientRequest) {
		
		return ClientRequest.from(clientRequest)
				.headers(h -> h.setBasicAuth("username", "password"))
				.build();
	}
	
	private ClientRequest withOAuth(ClientRequest clientRequest) {
		
		return ClientRequest.from(clientRequest)
				.headers(h -> h.setBearerAuth("some-long-jwt"))
				.build();
	}
}
