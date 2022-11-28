package com.liam.webfluxdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.liam.webfluxdemo.dtos.MultiplyRequestDto;
import com.liam.webfluxdemo.dtos.Response;

public class L03PostRequestTest extends BaseTest {
	
	@Autowired
	private WebClient webClient;
	
	
	@Test
	public void postTest() {
		
		webClient
			.post()
			.uri("reactive-math/multiply")
			.bodyValue(buiRequestDto(5, 2))
			.retrieve()
			.bodyToMono(Response.class)
			.doOnNext(System.out::println);
		
	}
	
	
	private MultiplyRequestDto buiRequestDto(int a, int b) {
		
		MultiplyRequestDto dto = new MultiplyRequestDto();
		
		dto.setFirst(a);
		dto.setSecond(b);
		
		return dto;
	}

}
