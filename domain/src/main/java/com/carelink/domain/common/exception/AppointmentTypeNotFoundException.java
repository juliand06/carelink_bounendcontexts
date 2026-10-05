package com.carelink.domain.common.exception;

import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;

public class AppointmentTypeNotFoundException extends RuntimeException {
    public AppointmentTypeNotFoundException(AppointmentTypeId id) {
        super("AppointmentType not found with id: " + id.value());
    }
}
