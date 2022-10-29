package com.liam.webfluxdemo.services;

import java.time.Duration;


import org.springframework.stereotype.Service;

import com.liam.webfluxdemo.dtos.MultiplyRequestDto;
import com.liam.webfluxdemo.dtos.Response;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveMathService {

	
	public Mono<Response> findSquare(int input) {
		return Mono.fromSupplier(() -> input * input)
				.map(x -> new Response(x))
				.log();
//				.map(Response::new);
	}
	
	
	public Flux<Response> multiplicationTable(int input) {
		
		
		return Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(1))
//				.doOnNext(x -> SleepUtil.sleepSeconds(1))
				.doOnNext(x -> System.out.println("Reactive MathService processing element: " + x))
				.map(x -> new Response(x * input))
				.log();
	}
	
	public Mono<Response> multiply(Mono<MultiplyRequestDto> dtoMono) {
		return dtoMono
					.map(dto -> dto.getFirst() * dto.getSecond())
//					.map(Response::new);
					.map(x -> new Response(x)) // Same as above
					.log();
	}
}
