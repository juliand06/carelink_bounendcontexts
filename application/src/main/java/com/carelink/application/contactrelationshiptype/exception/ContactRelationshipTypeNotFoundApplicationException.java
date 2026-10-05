package com.carelink.application.contactrelationshiptype.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class ContactRelationshipTypeNotFoundApplicationException extends NotFoundApplicationException {
    public ContactRelationshipTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
