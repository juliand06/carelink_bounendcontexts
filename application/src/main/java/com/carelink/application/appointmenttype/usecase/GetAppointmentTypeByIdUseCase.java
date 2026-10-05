package com.carelink.application.appointmenttype.usecase;

import com.carelink.application.appointmenttype.dto.AppointmentTypeResponse;
import com.carelink.domain.appointmenttype.port.repository.AppointmentTypeRepository;
import com.carelink.application.appointmenttype.exception.AppointmentTypeNotFoundApplicationException;
import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;

public class GetAppointmentTypeByIdUseCase {
    private final AppointmentTypeRepository repository;

    public GetAppointmentTypeByIdUseCase(AppointmentTypeRepository repository) {
        this.repository = repository;
    }

    public AppointmentTypeResponse execute(AppointmentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AppointmentTypeNotFoundApplicationException(id.value().toString()));
        return new AppointmentTypeResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.label(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
