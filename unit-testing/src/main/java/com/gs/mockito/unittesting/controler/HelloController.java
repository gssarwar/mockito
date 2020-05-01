package com.gs.mockito.unittesting.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/user/{name}")
	public String syHello(@PathVariable String name ) {
		return "hello ::"+ name;
	}
}
