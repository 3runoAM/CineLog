package com.brunoam.CineLog.dto.request.follow;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FollowRequestDTO(
        @NotNull @Positive Long followedId,
        @NotNull @Positive Long followerId
) {
}
