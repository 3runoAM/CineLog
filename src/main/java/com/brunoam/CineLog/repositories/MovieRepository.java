package com.brunoam.CineLog.repositories;

import com.brunoam.CineLog.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    //
}
