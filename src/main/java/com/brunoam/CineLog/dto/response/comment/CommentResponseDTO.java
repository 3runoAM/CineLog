package com.brunoam.CineLog.dto.response.comment;

import com.brunoam.CineLog.dto.response.user.UserResponseDTO;

public record CommentResponseDTO(
        Long id,
        String text,
        UserResponseDTO user,
        Long reviewId,
        Long listId
) {
}