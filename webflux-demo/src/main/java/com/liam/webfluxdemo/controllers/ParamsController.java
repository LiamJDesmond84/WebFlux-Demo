package com.liam.webfluxdemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jobs")
public class ParamsController {

	
	@GetMapping("/search")
	public void searchJobs(@RequestParam("count") int count, @RequestParam("age") int age) {
		
		
		
	}
}
