package com.carelink.domain.common.exception;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(AppointmentId id) {
        super("Appointment not found with id: " + id.value());
    }
}
