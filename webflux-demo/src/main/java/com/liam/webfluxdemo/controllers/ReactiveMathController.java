package com.liam.webfluxdemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liam.webfluxdemo.dtos.Response;
import com.liam.webfluxdemo.services.ReactiveMathService;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {
	
	@Autowired
	private ReactiveMathService reactiveMathService;
	
	
	@GetMapping("square/{input}")
	public Mono<Response> findSquare(@PathVariable int input) {
		
		return reactiveMathService.findSquare(input);
	}
	
	
	
	@GetMapping("table/{input}")
	public List<Response> multiplicationTable(@PathVariable int input) {
		
		return mathService.multiplicationTable(input);
	}

}
