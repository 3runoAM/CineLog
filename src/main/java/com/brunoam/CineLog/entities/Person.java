package com.brunoam.CineLog.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.util.Set;
import java.util.UUID;

@Entity
public class Person extends AuditableEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    @NotNull
    @Positive
    @Column(unique = true)
    private Long tmdbId;

    @NotBlank
    @Column(length = 50)
    @Length(max = 50)
    private String name;

    @URL
    private String profilePhotoUrl;

    @ManyToMany(mappedBy = "directors")
    private Set<Movie> directedMovies;

    @ManyToMany(mappedBy = "writers")
    private Set<Movie> writtenMovies;

    @ManyToMany(mappedBy = "cast")
    private Set<Movie> actedMovies;
}