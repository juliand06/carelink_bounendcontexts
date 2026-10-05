package com.carelink.application.appointmentstatus.usecase;

import java.time.LocalDateTime;

import com.carelink.application.appointmentstatus.exception.AppointmentStatusNotFoundApplicationException;
import com.carelink.domain.appointmentstatus.event.AppointmentStatusDeletedEvent;
import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;
import com.carelink.domain.appointmentstatus.port.repository.AppointmentStatusRepository;

public class DeleteAppointmentStatusUseCase {
    private final AppointmentStatusRepository repository;

    public DeleteAppointmentStatusUseCase(AppointmentStatusRepository repository) {
        this.repository = repository;
    }

    public AppointmentStatusDeletedEvent execute(AppointmentStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AppointmentStatusNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new AppointmentStatusDeletedEvent(id, LocalDateTime.now());
    }
}
