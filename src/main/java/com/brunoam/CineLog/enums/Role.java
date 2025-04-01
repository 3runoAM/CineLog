package com.brunoam.CineLog.enums;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN("Administrador"),
    ROLE_USER("Usuário");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}