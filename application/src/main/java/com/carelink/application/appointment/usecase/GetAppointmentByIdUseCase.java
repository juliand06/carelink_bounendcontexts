package com.carelink.application.appointment.usecase;

import com.carelink.application.appointment.dto.AppointmentResponse;
import com.carelink.domain.appointment.port.repository.AppointmentRepository;
import com.carelink.application.appointment.exception.AppointmentNotFoundApplicationException;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;

public class GetAppointmentByIdUseCase {
    private final AppointmentRepository repository;

    public GetAppointmentByIdUseCase(AppointmentRepository repository) {
        this.repository = repository;
    }

    public AppointmentResponse execute(AppointmentId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundApplicationException(id.value().toString()));
        return new AppointmentResponse(
                aggregate.id().value(),
                aggregate.healthRecordId().value(),
                aggregate.specialistId().value(),
                aggregate.appointmentTypeId().value(),
                aggregate.startedAt(),
                aggregate.endedAt(),
                aggregate.chiefComplaint(),
                aggregate.currentCondition(),
                aggregate.modalityId().value(),
                aggregate.statusId().value(),
                aggregate.createdAt(),
                aggregate.createdBy().value(),
                aggregate.updatedAt(),
                aggregate.updatedBy() == null ? null : aggregate.updatedBy().value());
    }
}
