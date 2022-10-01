package com.liam.webfluxdemo.services;

import org.springframework.stereotype.Service;

import com.liam.webfluxdemo.dtos.Response;

import reactor.core.publisher.Mono;

@Service
public class ReactiveMathService {

	
	public Mono<Response> findSquare(int input) {
		return Mono.fromSupplier(() -> input * input)
				.map(x -> new Response(x));
//				.map(Response::new);
	}
}
