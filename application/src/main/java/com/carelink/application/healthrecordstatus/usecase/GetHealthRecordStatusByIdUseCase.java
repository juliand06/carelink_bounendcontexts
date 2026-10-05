package com.carelink.application.healthrecordstatus.usecase;

import com.carelink.application.healthrecordstatus.dto.HealthRecordStatusResponse;
import com.carelink.domain.healthrecordstatus.port.repository.HealthRecordStatusRepository;
import com.carelink.application.healthrecordstatus.exception.HealthRecordStatusNotFoundApplicationException;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;

public class GetHealthRecordStatusByIdUseCase {
    private final HealthRecordStatusRepository repository;

    public GetHealthRecordStatusByIdUseCase(HealthRecordStatusRepository repository) {
        this.repository = repository;
    }

    public HealthRecordStatusResponse execute(HealthRecordStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new HealthRecordStatusNotFoundApplicationException(id.value().toString()));
        return new HealthRecordStatusResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.label(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
