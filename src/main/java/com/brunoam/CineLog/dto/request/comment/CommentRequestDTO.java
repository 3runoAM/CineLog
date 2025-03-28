package com.brunoam.CineLog.dto.request.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentRequestDTO(
        @NotBlank @Size(max = 250) String text,
        Long reviewId,
        Long listId
) {
}
