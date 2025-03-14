package com.brunoam.CineLog.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Range(min = 1, max = 5)
    private Integer rating;

    @Size(max = 1000)
    private String content;

    private Boolean watched;

    private LocalDate watchedDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    private Movie movie;

    @OneToMany
    private List<Like> likes;

    @OneToMany
    private List<Comment> comments;
}
