package com.carelink.application.appointmentmodality.usecase;

import com.carelink.application.appointmentmodality.command.RegisterAppointmentModalityCommand;
import com.carelink.application.appointmentmodality.dto.AppointmentModalityResponse;
import com.carelink.domain.appointmentmodality.port.repository.AppointmentModalityRepository;
import com.carelink.domain.appointmentmodality.model.aggregate.AppointmentModality;

public class RegisterAppointmentModalityUseCase {
    private final AppointmentModalityRepository repository;

    public RegisterAppointmentModalityUseCase(AppointmentModalityRepository repository) {
        this.repository = repository;
    }

    public AppointmentModalityResponse execute(RegisterAppointmentModalityCommand command) {
        AppointmentModality aggregate = AppointmentModality.register(
                command.code(),
                command.label(),
                command.active());
        AppointmentModality saved = repository.save(aggregate);
        return new AppointmentModalityResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
