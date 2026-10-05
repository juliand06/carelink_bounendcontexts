package com.carelink.application.appointment.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class AppointmentNotFoundApplicationException extends NotFoundApplicationException {
    public AppointmentNotFoundApplicationException(String message) {
        super(message);
    }
}
