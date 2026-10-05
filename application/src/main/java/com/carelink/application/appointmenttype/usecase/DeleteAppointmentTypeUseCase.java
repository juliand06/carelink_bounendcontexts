package com.carelink.application.appointmenttype.usecase;

import java.time.LocalDateTime;

import com.carelink.application.appointmenttype.exception.AppointmentTypeNotFoundApplicationException;
import com.carelink.domain.appointmenttype.event.AppointmentTypeDeletedEvent;
import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;
import com.carelink.domain.appointmenttype.port.repository.AppointmentTypeRepository;

public class DeleteAppointmentTypeUseCase {
    private final AppointmentTypeRepository repository;

    public DeleteAppointmentTypeUseCase(AppointmentTypeRepository repository) {
        this.repository = repository;
    }

    public AppointmentTypeDeletedEvent execute(AppointmentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AppointmentTypeNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new AppointmentTypeDeletedEvent(id, LocalDateTime.now());
    }
}
