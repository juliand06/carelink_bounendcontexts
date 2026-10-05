package com.carelink.application.country.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class CountryNotFoundApplicationException extends NotFoundApplicationException {
    public CountryNotFoundApplicationException(String message) {
        super(message);
    }
}
