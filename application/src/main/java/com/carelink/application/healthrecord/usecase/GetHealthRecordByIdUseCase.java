package com.carelink.application.healthrecord.usecase;

import com.carelink.application.healthrecord.dto.HealthRecordResponse;
import com.carelink.domain.healthrecord.port.repository.HealthRecordRepository;
import com.carelink.application.healthrecord.exception.HealthRecordNotFoundApplicationException;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;

public class GetHealthRecordByIdUseCase {
    private final HealthRecordRepository repository;

    public GetHealthRecordByIdUseCase(HealthRecordRepository repository) {
        this.repository = repository;
    }

    public HealthRecordResponse execute(HealthRecordId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new HealthRecordNotFoundApplicationException(id.value().toString()));
        return new HealthRecordResponse(
                aggregate.id().value(),
                aggregate.clientId().value(),
                aggregate.openingDate(),
                aggregate.recordCode(),
                aggregate.openedAt(),
                aggregate.closedAt(),
                aggregate.statusId().value(),
                aggregate.createdAt(),
                aggregate.createdBy().value());
    }
}
