package com.brunoam.CineLog;

import jakarta.persistence.GeneratedValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("/")
public class CineLogApplication {
	public static void main(String[] args) {
		SpringApplication.run(CineLogApplication.class, args);
	}

	@GetMapping
	public static String sayHello() {
		return "<h1>Hello CineLog!</h1>";
	}
}