package com.carelink.domain.security.exception;

public class UserNotFoundException extends DomainSecurityException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
