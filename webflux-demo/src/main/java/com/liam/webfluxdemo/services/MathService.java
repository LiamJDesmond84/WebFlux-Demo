package com.liam.webfluxdemo.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.liam.webfluxdemo.dtos.Response;

@Service
public class MathService {
	
	public Response findSquare(int input) {
		
		return new Response(input * input);
	}
	
	public List<Response> multiplicationTable(int input) {
		
		return IntStream.rangeClosed(1, 10)
				.mapToObj(x -> new Response(x * input))
				.collect(Collectors.toList());
		
	}

}
