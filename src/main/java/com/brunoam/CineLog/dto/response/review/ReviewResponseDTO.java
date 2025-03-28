package com.brunoam.CineLog.dto.response.review;

import com.brunoam.CineLog.dto.response.comment.CommentResponseDTO;
import com.brunoam.CineLog.entities.Like;

import java.time.LocalDate;
import java.util.List;

public record ReviewResponseDTO(
        Long id,
        Integer rating,
        String content,
        LocalDate watchedDate,
        boolean watched,
        Long movieId,
        Long userId,
        List<Like> likeList,
        List<CommentResponseDTO> commentList
) {
}