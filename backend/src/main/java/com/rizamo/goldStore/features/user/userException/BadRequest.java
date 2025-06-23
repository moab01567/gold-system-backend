package com.rizamo.goldStore.features.user.userException;

public class BadRequest extends RuntimeException {
    public BadRequest(String message) {
        super(message);
    }
}
