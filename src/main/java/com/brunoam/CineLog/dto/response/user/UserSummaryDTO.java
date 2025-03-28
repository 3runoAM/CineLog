package com.brunoam.CineLog.dto.response.user;

public record UserSummaryDTO(
        Long id,
        String firstName,
        String lastName,
        String email
) {
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
