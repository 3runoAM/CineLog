package com.brunoam.CineLog.exceptions;

public class InvalidImageException extends Exception {
    public InvalidImageException(String message) {
        super(message);
    }

    public InvalidImageException(String message, Throwable cause) {
        super(message, cause);
    }
}