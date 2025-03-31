package com.brunoam.CineLog.dto.response.movie;

import com.brunoam.CineLog.entities.Movie;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public record MovieSearchSummary(
        List<Movie> results,
        int page,
        int totalResults,
        int totalPages
) {
}
