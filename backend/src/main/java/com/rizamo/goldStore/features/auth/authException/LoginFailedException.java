package com.rizamo.goldStore.features.auth.authException;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException() {
        super("UNAUTHORIZED");
    }
}
