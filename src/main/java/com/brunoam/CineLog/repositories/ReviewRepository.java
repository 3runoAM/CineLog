package com.brunoam.CineLog.repositories;

import com.brunoam.CineLog.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //
}
