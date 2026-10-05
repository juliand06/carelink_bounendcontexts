package com.carelink.application.appointment.usecase;

import com.carelink.application.appointment.command.RegisterAppointmentCommand;
import com.carelink.application.appointment.dto.AppointmentResponse;
import com.carelink.domain.appointment.port.repository.AppointmentRepository;
import com.carelink.domain.appointment.model.aggregate.Appointment;

public class RegisterAppointmentUseCase {
    private final AppointmentRepository repository;

    public RegisterAppointmentUseCase(AppointmentRepository repository) {
        this.repository = repository;
    }

    public AppointmentResponse execute(RegisterAppointmentCommand command) {
        Appointment aggregate = Appointment.register(
                command.healthRecordId(),
                command.specialistId(),
                command.appointmentTypeId(),
                command.startedAt(),
                command.endedAt(),
                command.chiefComplaint(),
                command.currentCondition(),
                command.modalityId(),
                command.statusId(),
                command.createdBy(),
                command.updatedBy());
        Appointment saved = repository.save(aggregate);
        return new AppointmentResponse(
                saved.id().value(),
                saved.healthRecordId().value(),
                saved.specialistId().value(),
                saved.appointmentTypeId().value(),
                saved.startedAt(),
                saved.endedAt(),
                saved.chiefComplaint(),
                saved.currentCondition(),
                saved.modalityId().value(),
                saved.statusId().value(),
                saved.createdAt(),
                saved.createdBy().value(),
                saved.updatedAt(),
                saved.updatedBy() == null ? null : saved.updatedBy().value());
    }
}
