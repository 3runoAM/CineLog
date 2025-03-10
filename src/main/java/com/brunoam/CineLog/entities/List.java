package com.brunoam.CineLog.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Column(nullable = true)
    private String description;

    private Boolean isPublic;

    @ManyToOne
    private User user;

    @ManyToOne
    private Movie movie;

    @OneToMany
    private java.util.List<Comment> comments;

    @OneToMany
    private java.util.List<Like> likes;
}