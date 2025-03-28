package com.brunoam.CineLog.dto.response.review;

public record ReviewSummaryDTO(
        Long id,
        Integer rating,
        String content,
        boolean watched,
        Long movieId,
        Long userId,
        Long likeCount,
        Long commentCount
) {
}
