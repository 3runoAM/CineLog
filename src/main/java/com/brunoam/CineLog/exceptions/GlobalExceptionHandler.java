package com.brunoam.CineLog.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ImageDeletionException.class)
    public ResponseEntity<String> handleImageDeletionException(ImageDeletionException e){
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler(UserProfileNotFound.class)
    public ResponseEntity<String> handleUserProfileNotFound(UserProfileNotFound e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body("Erro de argumento: " + e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity.status(404)
                .body("Usuário não encontrado: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(500)
                .body("Ocorreu um erro inesperado: " + e.getMessage());
    }
}
