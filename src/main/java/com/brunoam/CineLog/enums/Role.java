package com.brunoam.CineLog.enums;

import lombok.Getter;

/**
 * Enumeração que representa os diferentes papéis de usuário no sistema.
 * Cada papel possui uma descrição escrita em linguagem humana.
 */
@Getter
public enum Role {
    ROLE_ADMIN("Administrador"),
    ROLE_USER("Usuário");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}