package com.example.demo.controllers;

import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// localhost:8080/
@RestController
@RequestMapping("/basic")
public class BasicController {
	
	//localhost:8080/basic
	@GetMapping("/something")
	public String basicHTMLResponse() {
		return "<h1>Hello World</h1>";
	}
	
	@GetMapping("/time")
	public LocalTime getTime() {
		return LocalTime.now();
	}
}
