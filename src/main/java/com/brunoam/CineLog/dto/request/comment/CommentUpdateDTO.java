package com.brunoam.CineLog.dto.request.comment;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateDTO(
        @NotBlank String text
) {
}
