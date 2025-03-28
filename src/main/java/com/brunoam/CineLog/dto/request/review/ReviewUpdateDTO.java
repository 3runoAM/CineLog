package com.brunoam.CineLog.dto.request.review;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

public record ReviewUpdateDTO(
        @Range(min= 1, max=5) Integer rating,
        @Size(max = 1000) String content,
        @PastOrPresent LocalDate watchedDate,
        boolean watched
) {
}
