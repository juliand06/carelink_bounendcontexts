package com.carelink.application.healthrecordstatus.usecase;

import java.time.LocalDateTime;

import com.carelink.application.healthrecordstatus.exception.HealthRecordStatusNotFoundApplicationException;
import com.carelink.domain.healthrecordstatus.event.HealthRecordStatusDeletedEvent;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;
import com.carelink.domain.healthrecordstatus.port.repository.HealthRecordStatusRepository;

public class DeleteHealthRecordStatusUseCase {
    private final HealthRecordStatusRepository repository;

    public DeleteHealthRecordStatusUseCase(HealthRecordStatusRepository repository) {
        this.repository = repository;
    }

    public HealthRecordStatusDeletedEvent execute(HealthRecordStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new HealthRecordStatusNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new HealthRecordStatusDeletedEvent(id, LocalDateTime.now());
    }
}
