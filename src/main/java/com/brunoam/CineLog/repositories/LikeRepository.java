package com.brunoam.CineLog.repositories;

import com.brunoam.CineLog.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    //
}
