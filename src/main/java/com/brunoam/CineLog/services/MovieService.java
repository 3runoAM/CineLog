package com.brunoam.CineLog.services;

import com.brunoam.CineLog.repositories.MovieRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
}
