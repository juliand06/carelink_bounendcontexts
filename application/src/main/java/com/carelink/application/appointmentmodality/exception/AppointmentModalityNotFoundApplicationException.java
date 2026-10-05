package com.carelink.application.appointmentmodality.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class AppointmentModalityNotFoundApplicationException extends NotFoundApplicationException {
    public AppointmentModalityNotFoundApplicationException(String message) {
        super(message);
    }
}
