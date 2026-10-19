package com.carelink.domain.security.exception;

public class DomainSecurityException extends RuntimeException {
    public DomainSecurityException(String message) {
        super(message);
    }

    public DomainSecurityException(String message, Throwable cause) {
        super(message, cause);
    }
}
