package com.brunoam.CineLog.services;

import com.brunoam.CineLog.repositories.MovieRepository;
import com.brunoam.CineLog.services.client.MovieApiClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieApiClient apiClient;

    public MovieService(MovieRepository movieRepository, MovieApiClient apiClient) {
        this.movieRepository = movieRepository;
        this.apiClient = apiClient;
    }


}
