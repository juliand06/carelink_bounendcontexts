package com.carelink.application.appointmentstatus.usecase;

import com.carelink.application.appointmentstatus.command.UpdateAppointmentStatusCommand;
import com.carelink.application.appointmentstatus.dto.AppointmentStatusResponse;
import com.carelink.domain.appointmentstatus.port.repository.AppointmentStatusRepository;
import com.carelink.application.appointmentstatus.exception.AppointmentStatusNotFoundApplicationException;

public class UpdateAppointmentStatusUseCase {
    private final AppointmentStatusRepository repository;

    public UpdateAppointmentStatusUseCase(AppointmentStatusRepository repository) {
        this.repository = repository;
    }

    public AppointmentStatusResponse execute(UpdateAppointmentStatusCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new AppointmentStatusNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.label(),
                command.active());
        var saved = repository.save(aggregate);
        return new AppointmentStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
