package com.carelink.domain.security.exception;

public class InvalidCredentialsException extends DomainSecurityException {
    public InvalidCredentialsException() {
        super("Credenciales inválidas");
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
