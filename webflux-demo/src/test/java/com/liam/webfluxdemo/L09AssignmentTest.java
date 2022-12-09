package com.liam.webfluxdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

public class L09AssignmentTest extends BaseTest {
	
	private static final String FORMAT = "%d %s %d = %s";
	
	private static final int A = 10;
	
	
	
	@Autowired
	private WebClient webClient;

	
	
}
