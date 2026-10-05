package com.carelink.application.appointmentmodality.usecase;

import java.util.List;

import com.carelink.application.appointmentmodality.dto.AppointmentModalityResponse;
import com.carelink.domain.appointmentmodality.port.repository.AppointmentModalityRepository;

public class ListAppointmentModalityUseCase {
    private final AppointmentModalityRepository repository;

    public ListAppointmentModalityUseCase(AppointmentModalityRepository repository) {
        this.repository = repository;
    }

    public List<AppointmentModalityResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new AppointmentModalityResponse(
                        aggregate.id().value(),
                        aggregate.code(),
                        aggregate.label(),
                        aggregate.active(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
