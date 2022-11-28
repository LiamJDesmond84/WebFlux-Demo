package com.liam.webfluxdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.liam.webfluxdemo.dtos.MultiplyRequestDto;

public class L03PostRequestTest extends BaseTest {
	
	@Autowired
	private WebClient webClient;
	
	
	@Test
	public void postTest(ServerRequest serverRequest) {
		
		Mono<Response> monoResponse = webClient
				.post()
				.uri()
		
	}
	
	
	private MultiplyRequestDto buiRequestDto(int a, int b) {
		
		MultiplyRequestDto dto = new MultiplyRequestDto();
		
		dto.setFirst(a);
		dto.setSecond(b);
		
		return dto;
	}

}
