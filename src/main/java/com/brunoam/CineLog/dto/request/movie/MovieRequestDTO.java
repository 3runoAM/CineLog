package com.brunoam.CineLog.dto.request.movie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.List;

public record MovieRequestDTO(
        Long tmdbId,
        @NotBlank @Size(max = 50) String title,
        @Positive Integer releaseYear,
        @NotBlank @Size(max = 50) String director,
        @NotBlank @Size(min = 10, max = 5000) String synopsis,
        @URL String posterURL,
        Boolean isCustom,
        List<Long> genresIds
) {
}