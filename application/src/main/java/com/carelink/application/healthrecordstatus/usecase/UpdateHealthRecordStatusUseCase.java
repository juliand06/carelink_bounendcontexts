package com.carelink.application.healthrecordstatus.usecase;

import com.carelink.application.healthrecordstatus.command.UpdateHealthRecordStatusCommand;
import com.carelink.application.healthrecordstatus.dto.HealthRecordStatusResponse;
import com.carelink.domain.healthrecordstatus.port.repository.HealthRecordStatusRepository;
import com.carelink.application.healthrecordstatus.exception.HealthRecordStatusNotFoundApplicationException;

public class UpdateHealthRecordStatusUseCase {
    private final HealthRecordStatusRepository repository;

    public UpdateHealthRecordStatusUseCase(HealthRecordStatusRepository repository) {
        this.repository = repository;
    }

    public HealthRecordStatusResponse execute(UpdateHealthRecordStatusCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new HealthRecordStatusNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.label());
        var saved = repository.save(aggregate);
        return new HealthRecordStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
