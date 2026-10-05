package com.carelink.application.careplanstatus.usecase;

import com.carelink.application.careplanstatus.dto.CarePlanStatusResponse;
import com.carelink.domain.careplanstatus.port.repository.CarePlanStatusRepository;
import com.carelink.application.careplanstatus.exception.CarePlanStatusNotFoundApplicationException;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;

public class GetCarePlanStatusByIdUseCase {
    private final CarePlanStatusRepository repository;

    public GetCarePlanStatusByIdUseCase(CarePlanStatusRepository repository) {
        this.repository = repository;
    }

    public CarePlanStatusResponse execute(CarePlanStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new CarePlanStatusNotFoundApplicationException(id.value().toString()));
        return new CarePlanStatusResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.label(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
