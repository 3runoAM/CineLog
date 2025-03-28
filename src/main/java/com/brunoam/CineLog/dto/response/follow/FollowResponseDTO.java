package com.brunoam.CineLog.dto.response.follow;

import com.brunoam.CineLog.dto.response.user.UserResponseDTO;

public record FollowResponseDTO(
        Long id,
        UserResponseDTO follower,
        UserResponseDTO followed
) {
}
