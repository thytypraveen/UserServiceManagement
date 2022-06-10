package com.usermanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	
	@GetMapping(value="/hello")
	public String say(){
		
		return "hello everyone";
	}

}
