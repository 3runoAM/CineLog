package com.brunoam.CineLog.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 250)
    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    @Column(nullable = true)
    private Review review;

    @ManyToOne
    @Column(nullable = true)
    private List list;
}
