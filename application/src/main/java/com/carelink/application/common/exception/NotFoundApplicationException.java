package com.carelink.application.common.exception;

/** Base de las excepciones "recurso no encontrado"; la capa web la traduce a HTTP 404. */
public abstract class NotFoundApplicationException extends ApplicationException {
    protected NotFoundApplicationException(String message) {
        super(message);
    }
}
