package com.brunoam.CineLog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class AlternativeTitle {
    @NotBlank
    private String title;

    @NotBlank
    @Column(length = 2)
    private String region;
}