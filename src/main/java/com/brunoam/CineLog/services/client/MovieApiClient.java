package com.brunoam.CineLog.services.client;

import com.brunoam.CineLog.dto.response.movie.MovieSearchSummary;
import com.brunoam.CineLog.entities.Movie;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MovieApiClient {
    private final WebClient tmdbClient;

    public MovieApiClient(WebClient tmdbClient) {
        this.tmdbClient = tmdbClient;
    }

    public Mono<Movie> getMovieDetails(int movieId) {
        return tmdbClient.get()
                .uri("/movie/{id}", movieId)
                .retrieve()
                .bodyToMono(Movie.class);
    }

    public Mono<List<Movie>> searchMovies(String query) {
        return tmdbClient.get()
                .uri("/search/movie?query=" + query)
                .retrieve()
                .bodyToMono(MovieSearchSummary.class)
                .map(MovieSearchSummary::results);
    }
}