package com.carelink.application.appointmenttype.usecase;

import com.carelink.application.appointmenttype.command.RegisterAppointmentTypeCommand;
import com.carelink.application.appointmenttype.dto.AppointmentTypeResponse;
import com.carelink.domain.appointmenttype.port.repository.AppointmentTypeRepository;
import com.carelink.domain.appointmenttype.model.aggregate.AppointmentType;

public class RegisterAppointmentTypeUseCase {
    private final AppointmentTypeRepository repository;

    public RegisterAppointmentTypeUseCase(AppointmentTypeRepository repository) {
        this.repository = repository;
    }

    public AppointmentTypeResponse execute(RegisterAppointmentTypeCommand command) {
        AppointmentType aggregate = AppointmentType.register(
                command.code(),
                command.label(),
                command.active());
        AppointmentType saved = repository.save(aggregate);
        return new AppointmentTypeResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
