package com.brunoam.CineLog.dto.request.list;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ListUpdateDTO(
        @NotBlank @Size(max = 50) String title,
        @Size(max = 500) String description,
        boolean isPublic
) {
}
