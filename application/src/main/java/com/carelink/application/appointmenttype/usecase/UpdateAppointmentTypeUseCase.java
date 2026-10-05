package com.carelink.application.appointmenttype.usecase;

import com.carelink.application.appointmenttype.command.UpdateAppointmentTypeCommand;
import com.carelink.application.appointmenttype.dto.AppointmentTypeResponse;
import com.carelink.domain.appointmenttype.port.repository.AppointmentTypeRepository;
import com.carelink.application.appointmenttype.exception.AppointmentTypeNotFoundApplicationException;

public class UpdateAppointmentTypeUseCase {
    private final AppointmentTypeRepository repository;

    public UpdateAppointmentTypeUseCase(AppointmentTypeRepository repository) {
        this.repository = repository;
    }

    public AppointmentTypeResponse execute(UpdateAppointmentTypeCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new AppointmentTypeNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.label(),
                command.active());
        var saved = repository.save(aggregate);
        return new AppointmentTypeResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
