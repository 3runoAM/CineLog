package com.brunoam.CineLog;


import com.brunoam.CineLog.services.client.MovieApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("/")
public class CineLogApplication {
	private final MovieApiClient tmdbClient;

	public CineLogApplication(MovieApiClient tmdbClient) {
		this.tmdbClient = tmdbClient;
	}
	public static void main(String[] args) {
		SpringApplication.run(CineLogApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
			tmdbClient.getMovieDetails(550) // Fight Club
					.subscribe(movie -> System.out.println("Filme encontrado: " + movie.getTitle()));

//			// Teste de busca por query
//			tmdbClient.searchMovies("Matrix")
//					.subscribe(movies -> movies.forEach(movie ->
//							System.out.println("Resultado da busca: " + movie)));
		};
	}

	@GetMapping
	public static String sayHello() {
		return "<h1>Hello CineLog!</h1>";
	}
}