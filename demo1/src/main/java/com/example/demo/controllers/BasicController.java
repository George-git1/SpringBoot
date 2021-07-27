package com.example.demo.controllers;

import java.time.LocalTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // indicates to spring this is a bean
/* @RequestMapping("/basic") */ 
public class BasicController {

	
	
	@GetMapping("/basic")
	public String basicHTMLResponse() {
		return "<h1>Hello World</h1>";
	}
	
	@GetMapping("/time")
	public LocalTime getTime() {
		return LocalTime.now();
		
	}
	
	
		
	}


