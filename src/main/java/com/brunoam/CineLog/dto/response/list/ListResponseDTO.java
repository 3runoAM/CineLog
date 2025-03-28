package com.brunoam.CineLog.dto.response.list;

import com.brunoam.CineLog.dto.response.comment.CommentResponseDTO;
import com.brunoam.CineLog.dto.response.movie.MovieResponseDTO;
import com.brunoam.CineLog.dto.response.user.UserResponseDTO;
import com.brunoam.CineLog.entities.Like;

import java.util.List;

public record ListResponseDTO(
        Long id,
        String title,
        String description,
        boolean isPublic,
        UserResponseDTO user,
        List<MovieResponseDTO> movies,
        List<CommentResponseDTO> comments,
        List<Like> likes
) {
}
