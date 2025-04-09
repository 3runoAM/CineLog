package com.brunoam.CineLog.entities;

import com.brunoam.CineLog.entities.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.URL;

import java.util.List;
import java.util.Set;
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

    @NotNull
    @Positive
    @Column(unique = true)
    private Long tmdbId;

    @NotBlank
    private String title;

    @NotNull
    @Positive
    private Integer releaseYear;

    @NotBlank
    @Column(length = 2000)
    private String overview;

    @NotBlank
    private String tagline;

    private String posterPath;

    private String backdropPath;

    @URL
    private String trailerUrl;

    @Positive
    @Size(min = 1)
    private Integer runtime;

    @NotBlank
    private String primaryLanguage;

    @ElementCollection
    private List<String> spokenLanguage;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    private Double ratingAverage;

    @ManyToMany
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<@Valid Genre> genres;

    @OneToMany
    private List<@Valid Review> reviews;

    @ManyToMany
    @JoinTable(name = "movie_director",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<@Valid Person> directors;

    @ManyToMany
    @JoinTable(name = "movie_writer",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<@Valid Person> writers;

    @ManyToMany
    @JoinTable(name = "movie_cast",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<@Valid Person> cast;

    @ManyToMany
    @JoinTable(name = "movie_theme",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    private Set<Theme> themes;

    @Embedded
    private MovieStats stats;

    @ElementCollection
    private List<AlternativeTitle> alternativeTitles;

    @ElementCollection
    private Set<String> productionCountries;

    @OneToMany(mappedBy = "movie")
    private List<ReleaseInfo> worldwideReleaseInfo;
}