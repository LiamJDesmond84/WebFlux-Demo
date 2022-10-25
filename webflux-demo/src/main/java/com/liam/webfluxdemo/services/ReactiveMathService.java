package com.liam.webfluxdemo.services;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

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
		
		List<Response> list = IntStream.rangeClosed(1, 10)
			.peek(x -> SleepUtil.sleepSeconds(1))
			.peek(x -> System.out.println("MathService processing element: " + x))
			.mapToObj(x -> new Response(x * input))
			.collect(Collectors.toList());
		return Flux.fromIterable(list);
		
//		return Flux.range(1, 10)
//				.delayElements(Duration.ofSeconds(1))
////				.doOnNext(x -> SleepUtil.sleepSeconds(1))
//				.doOnNext(x -> System.out.println("Reactive MathService processing element: " + x))
//				.map(x -> new Response(x * input))
//				.log();
	}
}
