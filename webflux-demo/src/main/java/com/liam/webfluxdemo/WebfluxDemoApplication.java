package com.liam.webfluxdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebfluxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxDemoApplication.class, args);
	}
	
	
	
	
	
	// IMPORTANT
		// Response - /math/table/{input} - Will not stop on cancel & will only return/collect, convert to JSON & return the items until it is completed.
		// Response - /reactive-math/table/{input} - Will stop on cancel but will only return/collect, convert to JSON & return the items until it is completed. - Asynchronous/non-blocking
		// Response - /reactive-math/table/{input}/stream - Can be cancelled anytime, will convert to JSON & return each item as it is emitted. JSON conversion for every single item. - Asynchronous/non-blocking

}
