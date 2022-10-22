package com.liam.webfluxdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liam.webfluxdemo.dtos.Response;
import com.liam.webfluxdemo.services.ReactiveMathService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Back-end will be acting as a Publisher
// Browser will be acting like a Subscriber
// Microservice to Microservice - Calling Microservice will be acting as a Subscriber
@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {
	
	@Autowired
	private ReactiveMathService reactiveMathService;
	
	
	// input * input
	@GetMapping("square/{input}")
	public Mono<Response> findSquare(@PathVariable int input) {
		
		return reactiveMathService.findSquare(input);
	}
	
	// Flux withOUT TEXT_EVENT_STREAM - Asynchronous/non-blocking - Can be cancelled anytime, but will only collect, convert to JSON & return the items until it is completed.
	
	// Flux WTIH TEXT_EVENT_STREAM - Asynchronous/non-blocking - Can be cancelled anytime, will convert to JSON & return each item as it is emitted.
	
	
	// input'a multiplication table up to 10(5 - > 5 to 50) - Not any faster than normal List - 10 seconds -> then sends everything.
	@GetMapping("table/{input}")
	public Flux<Response> multiplicationTable(@PathVariable int input) {
		// Without MediaType.TEXT_EVENT_STREAM_VALUE - it still Collects.toList & will not return until it is complete(10 seconds in this case), so it will not exactly stream
		return reactiveMathService.multiplicationTable(input);
	}
	
	// input'a multiplication table up to 10(5 - > 5 to 50) - Does not wait for the 10 seconds -> When back-end service completes something, it pushes the value to our Subscriber(browser) - 1 at a time.
	@GetMapping(value = "table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE) // <-------------------------------------- THIS
	public Flux<Response> multiplicationTableStream(@PathVariable int input) {
		
		return reactiveMathService.multiplicationTable(input);
	}

}
