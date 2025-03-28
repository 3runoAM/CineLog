package com.brunoam.CineLog.dto.response.user;

import com.brunoam.CineLog.dto.response.list.ListResponseDTO;
import com.brunoam.CineLog.dto.response.movie.MovieResponseDTO;
import com.brunoam.CineLog.dto.response.review.ReviewResponseDTO;

import java.util.List;

public record UserStatsDTO(
        List<ReviewResponseDTO> reviews,
        List<ListResponseDTO> lists,
        List<MovieResponseDTO> moviesLiked
) {
}