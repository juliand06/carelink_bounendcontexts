package com.carelink.application.contactemailaddress.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class ContactEmailAddressNotFoundApplicationException extends NotFoundApplicationException {
    public ContactEmailAddressNotFoundApplicationException(String message) {
        super(message);
    }
}
