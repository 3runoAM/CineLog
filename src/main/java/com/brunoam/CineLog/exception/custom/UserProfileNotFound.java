package com.brunoam.CineLog.exception.custom;

public class UserProfileNotFound extends RuntimeException {
    public UserProfileNotFound(String message){
        super(message);
    }
}