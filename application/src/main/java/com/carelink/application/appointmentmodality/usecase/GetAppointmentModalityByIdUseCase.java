package com.carelink.application.appointmentmodality.usecase;

import com.carelink.application.appointmentmodality.dto.AppointmentModalityResponse;
import com.carelink.domain.appointmentmodality.port.repository.AppointmentModalityRepository;
import com.carelink.application.appointmentmodality.exception.AppointmentModalityNotFoundApplicationException;
import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;

public class GetAppointmentModalityByIdUseCase {
    private final AppointmentModalityRepository repository;

    public GetAppointmentModalityByIdUseCase(AppointmentModalityRepository repository) {
        this.repository = repository;
    }

    public AppointmentModalityResponse execute(AppointmentModalityId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AppointmentModalityNotFoundApplicationException(id.value().toString()));
        return new AppointmentModalityResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.label(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
