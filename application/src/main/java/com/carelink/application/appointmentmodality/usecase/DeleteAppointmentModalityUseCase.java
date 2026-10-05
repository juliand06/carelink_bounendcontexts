package com.carelink.application.appointmentmodality.usecase;

import java.time.LocalDateTime;

import com.carelink.application.appointmentmodality.exception.AppointmentModalityNotFoundApplicationException;
import com.carelink.domain.appointmentmodality.event.AppointmentModalityDeletedEvent;
import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;
import com.carelink.domain.appointmentmodality.port.repository.AppointmentModalityRepository;

public class DeleteAppointmentModalityUseCase {
    private final AppointmentModalityRepository repository;

    public DeleteAppointmentModalityUseCase(AppointmentModalityRepository repository) {
        this.repository = repository;
    }

    public AppointmentModalityDeletedEvent execute(AppointmentModalityId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AppointmentModalityNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new AppointmentModalityDeletedEvent(id, LocalDateTime.now());
    }
}
