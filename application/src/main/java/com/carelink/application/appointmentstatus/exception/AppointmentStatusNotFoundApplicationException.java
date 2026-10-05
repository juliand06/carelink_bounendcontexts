package com.carelink.application.appointmentstatus.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class AppointmentStatusNotFoundApplicationException extends NotFoundApplicationException {
    public AppointmentStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
