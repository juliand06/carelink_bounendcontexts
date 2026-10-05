package com.carelink.domain.common.exception;

import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;

public class AppointmentModalityNotFoundException extends RuntimeException {
    public AppointmentModalityNotFoundException(AppointmentModalityId id) {
        super("AppointmentModality not found with id: " + id.value());
    }
}
