package com.brunoam.CineLog.entities;

import com.brunoam.CineLog.entities.base.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends AuditableEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    @Column(unique = true)
    private Long tmdbId;

    @NotBlank
    private String originalTitle;

    @NotNull
    @Positive
    private Integer releaseYear;

    @NotBlank
    @Column(length = 2000)
    private String overview;

    @NotBlank
    private String tagline;

    @URL
    private String posterPath;

    @URL
    private String backdropImgPath;

    @Positive
    @Size(min = 1)
    private Integer runtime;

    @NotBlank
    private String originalLanguage;

    @URL
    private String trailerUrl;

    private Double userRating;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    private Double ratingAverage;

//    private Set<@Valid Genre> genres;
//    private List<@Valid Review> reviews;
//    private List<@Valid Person> directors;
//    private List<@Valid Person> writers;
//    private List<@Valid Person> cast;
//
//    private List<String> tags;
//
//    private Boolean isWatchlisted;
//
//    private Boolean isWatched;
//
//    @ElementCollection
//    private List<WatchProvider> watchProviders;
//
//    @Embedded
//    private MovieStats stats;
}
