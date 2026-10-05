package com.carelink.application.appointmentstatus.usecase;

import com.carelink.application.appointmentstatus.dto.AppointmentStatusResponse;
import com.carelink.domain.appointmentstatus.port.repository.AppointmentStatusRepository;
import com.carelink.application.appointmentstatus.exception.AppointmentStatusNotFoundApplicationException;
import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;

public class GetAppointmentStatusByIdUseCase {
    private final AppointmentStatusRepository repository;

    public GetAppointmentStatusByIdUseCase(AppointmentStatusRepository repository) {
        this.repository = repository;
    }

    public AppointmentStatusResponse execute(AppointmentStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AppointmentStatusNotFoundApplicationException(id.value().toString()));
        return new AppointmentStatusResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.label(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
