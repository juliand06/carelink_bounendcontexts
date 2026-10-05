package com.carelink.application.appointment.usecase;

import java.time.LocalDateTime;

import com.carelink.application.appointment.exception.AppointmentNotFoundApplicationException;
import com.carelink.domain.appointment.event.AppointmentDeletedEvent;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.appointment.port.repository.AppointmentRepository;

public class DeleteAppointmentUseCase {
    private final AppointmentRepository repository;

    public DeleteAppointmentUseCase(AppointmentRepository repository) {
        this.repository = repository;
    }

    public AppointmentDeletedEvent execute(AppointmentId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new AppointmentDeletedEvent(id, LocalDateTime.now());
    }
}
