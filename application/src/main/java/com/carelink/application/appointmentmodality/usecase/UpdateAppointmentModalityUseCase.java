package com.carelink.application.appointmentmodality.usecase;

import com.carelink.application.appointmentmodality.command.UpdateAppointmentModalityCommand;
import com.carelink.application.appointmentmodality.dto.AppointmentModalityResponse;
import com.carelink.domain.appointmentmodality.port.repository.AppointmentModalityRepository;
import com.carelink.application.appointmentmodality.exception.AppointmentModalityNotFoundApplicationException;

public class UpdateAppointmentModalityUseCase {
    private final AppointmentModalityRepository repository;

    public UpdateAppointmentModalityUseCase(AppointmentModalityRepository repository) {
        this.repository = repository;
    }

    public AppointmentModalityResponse execute(UpdateAppointmentModalityCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new AppointmentModalityNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.label(),
                command.active());
        var saved = repository.save(aggregate);
        return new AppointmentModalityResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
