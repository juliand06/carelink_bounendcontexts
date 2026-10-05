package com.carelink.application.appointmentstatus.usecase;

import java.util.List;

import com.carelink.application.appointmentstatus.dto.AppointmentStatusResponse;
import com.carelink.domain.appointmentstatus.port.repository.AppointmentStatusRepository;

public class ListAppointmentStatusUseCase {
    private final AppointmentStatusRepository repository;

    public ListAppointmentStatusUseCase(AppointmentStatusRepository repository) {
        this.repository = repository;
    }

    public List<AppointmentStatusResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new AppointmentStatusResponse(
                        aggregate.id().value(),
                        aggregate.code(),
                        aggregate.label(),
                        aggregate.active(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
