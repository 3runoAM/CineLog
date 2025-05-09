package com.brunoam.CineLog.repositories;

import com.brunoam.CineLog.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
    Optional<UserProfile> findByAuthUser_Email(String username);

    boolean existsByAuthUser_Email(String email);
}