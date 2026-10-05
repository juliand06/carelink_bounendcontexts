package com.carelink.application.senderrole.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class SenderRoleNotFoundApplicationException extends NotFoundApplicationException {
    public SenderRoleNotFoundApplicationException(String message) {
        super(message);
    }
}
