package com.rizamo.goldStore.features.auth.authException;

public class NotValidJwtTokenException extends RuntimeException {
    public NotValidJwtTokenException(String message) {
        super(message);
    }
}
