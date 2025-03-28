package com.brunoam.CineLog.dto.request.comment;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateDto(
        @NotBlank String text
) {
}
