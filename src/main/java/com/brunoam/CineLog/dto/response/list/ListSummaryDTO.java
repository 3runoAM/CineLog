package com.brunoam.CineLog.dto.response.list;

import com.brunoam.CineLog.dto.response.user.UserResponseDTO;

public record ListSummaryDTO(
        Long id,
        String name,
        String description,
        boolean isPublic,
        UserResponseDTO user,
        Long movieCount,
        Long likeCount
) {
}
