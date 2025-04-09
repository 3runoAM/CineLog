package com.brunoam.CineLog.entities;

import com.brunoam.CineLog.enums.ReleaseType;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class ReleaseInfo extends AuditableEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    private LocalDate releaseDate;

    @Column(length = 2)
    private String region;

    @Enumerated(EnumType.STRING)
    private ReleaseType type;

    private String notes;
}