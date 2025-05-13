package com.brunoam.CineLog.exception;

import com.brunoam.CineLog.exception.custom.EntityDeletionException;
import com.brunoam.CineLog.exception.custom.ImageDeletionException;
import com.brunoam.CineLog.exception.custom.InvalidTokenException;
import com.brunoam.CineLog.exception.custom.UserProfileNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ImageDeletionException.class)
    public ResponseEntity<String> handleImageDeletionException(ImageDeletionException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(UserProfileNotFound.class)
    public ResponseEntity<String> handleUserProfileNotFound(UserProfileNotFound e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body("Erro de argumento: " + e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Usuário não encontrado: " + e.getMessage());
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<String> handleInvalidTokenException(InvalidTokenException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Token inválido: " + e.getMessage());
    }

    @ExceptionHandler(EntityDeletionException.class)
    public
    ResponseEntity<String> handleEntityDeletionException(EntityDeletionException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao deletar entidade: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro inesperado: " + e.getMessage());
    }
}
