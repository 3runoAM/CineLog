package com.brunoam.CineLog.repositories;

import com.brunoam.CineLog.entities.UserEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserEntryRepository extends JpaRepository<UserEntry, UUID> {
}
