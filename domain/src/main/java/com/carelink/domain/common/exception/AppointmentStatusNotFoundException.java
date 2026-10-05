package com.carelink.domain.common.exception;

import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;

public class AppointmentStatusNotFoundException extends RuntimeException {
    public AppointmentStatusNotFoundException(AppointmentStatusId id) {
        super("AppointmentStatus not found with id: " + id.value());
    }
}
