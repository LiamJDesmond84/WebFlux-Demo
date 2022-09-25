package com.liam.webfluxdemo.services;

import org.springframework.stereotype.Service;

import com.liam.webfluxdemo.dtos.Response;

@Service
public class MathService {
	
	public Response findSquare(int input) {
		
		return new Response(input * input);
	}

}
