package com.carelink.application.appointment.usecase;

import com.carelink.application.appointment.command.UpdateAppointmentCommand;
import com.carelink.application.appointment.dto.AppointmentResponse;
import com.carelink.domain.appointment.port.repository.AppointmentRepository;
import com.carelink.application.appointment.exception.AppointmentNotFoundApplicationException;

public class UpdateAppointmentUseCase {
    private final AppointmentRepository repository;

    public UpdateAppointmentUseCase(AppointmentRepository repository) {
        this.repository = repository;
    }

    public AppointmentResponse execute(UpdateAppointmentCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new AppointmentNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.healthRecordId(),
                command.specialistId(),
                command.appointmentTypeId(),
                command.startedAt(),
                command.endedAt(),
                command.chiefComplaint(),
                command.currentCondition(),
                command.modalityId(),
                command.statusId(),
                command.updatedBy());
        var saved = repository.save(aggregate);
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
