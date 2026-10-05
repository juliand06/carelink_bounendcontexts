package com.carelink.application.appointmentstatus.usecase;

import com.carelink.application.appointmentstatus.command.RegisterAppointmentStatusCommand;
import com.carelink.application.appointmentstatus.dto.AppointmentStatusResponse;
import com.carelink.domain.appointmentstatus.port.repository.AppointmentStatusRepository;
import com.carelink.domain.appointmentstatus.model.aggregate.AppointmentStatus;

public class RegisterAppointmentStatusUseCase {
    private final AppointmentStatusRepository repository;

    public RegisterAppointmentStatusUseCase(AppointmentStatusRepository repository) {
        this.repository = repository;
    }

    public AppointmentStatusResponse execute(RegisterAppointmentStatusCommand command) {
        AppointmentStatus aggregate = AppointmentStatus.register(
                command.code(),
                command.label(),
                command.active());
        AppointmentStatus saved = repository.save(aggregate);
        return new AppointmentStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
