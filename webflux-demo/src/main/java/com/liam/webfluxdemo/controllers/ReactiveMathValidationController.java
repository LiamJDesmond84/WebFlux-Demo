package com.liam.webfluxdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liam.webfluxdemo.dtos.Response;
import com.liam.webfluxdemo.exceptions.InputValidationException;
import com.liam.webfluxdemo.services.ReactiveMathService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathValidationController {
	
	@Autowired
	private ReactiveMathService reactiveMathService;
	
	
	// input * input
	@GetMapping("square/{input}/throw")
	public Mono<Response> findSquare(@PathVariable int input) {
		if(input < 10 || input > 20) {
			
			throw new InputValidationException(input);
			
		}
		
		
		return reactiveMathService.findSquare(input);
	}

}
