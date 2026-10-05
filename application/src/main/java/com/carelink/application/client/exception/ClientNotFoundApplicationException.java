package com.carelink.application.client.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class ClientNotFoundApplicationException extends NotFoundApplicationException {
    public ClientNotFoundApplicationException(String message) {
        super(message);
    }
}
