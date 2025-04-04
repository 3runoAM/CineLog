package com.brunoam.CineLog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/main")
public class CineLogApplication {
	public static void main(String[] args) {
		SpringApplication.run(CineLogApplication.class, args);
	}

	@GetMapping
	public String home() {
		return "<h1>CineLog API is running!</h1>";
	}
}