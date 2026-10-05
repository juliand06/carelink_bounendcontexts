package com.carelink.application.healthrecord.usecase;

import java.time.LocalDateTime;

import com.carelink.application.healthrecord.exception.HealthRecordNotFoundApplicationException;
import com.carelink.domain.healthrecord.event.HealthRecordDeletedEvent;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;
import com.carelink.domain.healthrecord.port.repository.HealthRecordRepository;

public class DeleteHealthRecordUseCase {
    private final HealthRecordRepository repository;

    public DeleteHealthRecordUseCase(HealthRecordRepository repository) {
        this.repository = repository;
    }

    public HealthRecordDeletedEvent execute(HealthRecordId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new HealthRecordNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new HealthRecordDeletedEvent(id, LocalDateTime.now());
    }
}
