package com.carelink.application.contactphonenumber.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class ContactPhoneNumberNotFoundApplicationException extends NotFoundApplicationException {
    public ContactPhoneNumberNotFoundApplicationException(String message) {
        super(message);
    }
}
