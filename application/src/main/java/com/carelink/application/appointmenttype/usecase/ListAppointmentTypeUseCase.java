package com.carelink.application.appointmenttype.usecase;

import java.util.List;

import com.carelink.application.appointmenttype.dto.AppointmentTypeResponse;
import com.carelink.domain.appointmenttype.port.repository.AppointmentTypeRepository;

public class ListAppointmentTypeUseCase {
    private final AppointmentTypeRepository repository;

    public ListAppointmentTypeUseCase(AppointmentTypeRepository repository) {
        this.repository = repository;
    }

    public List<AppointmentTypeResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new AppointmentTypeResponse(
                        aggregate.id().value(),
                        aggregate.code(),
                        aggregate.label(),
                        aggregate.active(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
