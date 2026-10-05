package com.carelink.application.healthrecordstatus.usecase;

import java.util.List;

import com.carelink.application.healthrecordstatus.dto.HealthRecordStatusResponse;
import com.carelink.domain.healthrecordstatus.port.repository.HealthRecordStatusRepository;

public class ListHealthRecordStatusUseCase {
    private final HealthRecordStatusRepository repository;

    public ListHealthRecordStatusUseCase(HealthRecordStatusRepository repository) {
        this.repository = repository;
    }

    public List<HealthRecordStatusResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new HealthRecordStatusResponse(
                        aggregate.id().value(),
                        aggregate.code(),
                        aggregate.label(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
