package com.brunoam.CineLog.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class MovieStats {
    private int watchCount;
    private int listAppearances;
    private int reviewCount;
}