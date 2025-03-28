package com.brunoam.CineLog.dto.response.movie;

public record MovieSearchDTO(
        Long id,
        String title,
        Integer releaseYear,
        String director,
        String synopsis,
        String posterURL,
        Boolean isCustom,
        Double averageRating
) {
}
