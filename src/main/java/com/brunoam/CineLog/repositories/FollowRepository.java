package com.brunoam.CineLog.repositories;

import com.brunoam.CineLog.entities.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    //
}
