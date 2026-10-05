package com.carelink.application.appointment.usecase;

import java.util.List;

import com.carelink.application.appointment.dto.AppointmentResponse;
import com.carelink.domain.appointment.port.repository.AppointmentRepository;

public class ListAppointmentUseCase {
    private final AppointmentRepository repository;

    public ListAppointmentUseCase(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<AppointmentResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new AppointmentResponse(
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
                        aggregate.updatedBy() == null ? null : aggregate.updatedBy().value()))
                .toList();
    }
}
