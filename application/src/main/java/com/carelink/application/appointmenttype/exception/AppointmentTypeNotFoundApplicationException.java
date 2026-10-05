package com.carelink.application.appointmenttype.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class AppointmentTypeNotFoundApplicationException extends NotFoundApplicationException {
    public AppointmentTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
