package com.brunoam.CineLog.dto.request.list;

import com.brunoam.CineLog.dto.response.user.UserResponseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ListRequestDTO(
        @NotBlank @Size(max = 50) String title,
        @Size(max = 500) String description,
        boolean isPublic,
        List<Long> movieIds
) {
}