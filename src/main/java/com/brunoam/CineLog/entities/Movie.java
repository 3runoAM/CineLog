package com.brunoam.CineLog.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long tmdbId;

    @NotBlank
    @Size(max = 50)
    private String title;

    @Positive
    private Integer releaseYear;

    @NotBlank
    @Size(max = 50)
    private String director;

    @Size(min = 10, max = 5000)
    @Column(columnDefinition = "TEXT")
    private String synopsis;

    @URL
    private String posterURL;

    private Boolean isCustom;

    private Double averageRating;

    private Double popularityScore;

    @OneToMany
    private List<Review> reviews;

    @ManyToMany
    private List<com.brunoam.CineLog.entities.List> lists;

    @ManyToMany
    private List<Genre> genres;
}