package com.brunoam.CineLog.dto.response.movie;

import java.util.List;

public record MovieResponseDTO(
        Long tmdbId,
        String title,
        Integer releaseYear,
        String director,
        String synopsis,
        String posterURL,
        Boolean isCustom,
        Double averageRating,
        Double popularityScore,
        List<Long> genres
) {
}
