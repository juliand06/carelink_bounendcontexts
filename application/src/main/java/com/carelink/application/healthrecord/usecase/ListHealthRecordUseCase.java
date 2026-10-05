package com.carelink.application.healthrecord.usecase;

import java.util.List;

import com.carelink.application.healthrecord.dto.HealthRecordResponse;
import com.carelink.domain.healthrecord.port.repository.HealthRecordRepository;

public class ListHealthRecordUseCase {
    private final HealthRecordRepository repository;

    public ListHealthRecordUseCase(HealthRecordRepository repository) {
        this.repository = repository;
    }

    public List<HealthRecordResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new HealthRecordResponse(
                        aggregate.id().value(),
                        aggregate.clientId().value(),
                        aggregate.openingDate(),
                        aggregate.recordCode(),
                        aggregate.openedAt(),
                        aggregate.closedAt(),
                        aggregate.statusId().value(),
                        aggregate.createdAt(),
                        aggregate.createdBy().value()))
                .toList();
    }
}
