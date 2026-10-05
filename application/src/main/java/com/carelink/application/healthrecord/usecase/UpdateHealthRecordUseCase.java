package com.carelink.application.healthrecord.usecase;

import com.carelink.application.healthrecord.command.UpdateHealthRecordCommand;
import com.carelink.application.healthrecord.dto.HealthRecordResponse;
import com.carelink.domain.healthrecord.port.repository.HealthRecordRepository;
import com.carelink.application.healthrecord.exception.HealthRecordNotFoundApplicationException;

public class UpdateHealthRecordUseCase {
    private final HealthRecordRepository repository;

    public UpdateHealthRecordUseCase(HealthRecordRepository repository) {
        this.repository = repository;
    }

    public HealthRecordResponse execute(UpdateHealthRecordCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new HealthRecordNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.clientId(),
                command.recordCode(),
                command.openedAt(),
                command.closedAt(),
                command.statusId());
        var saved = repository.save(aggregate);
        return new HealthRecordResponse(
                saved.id().value(),
                saved.clientId().value(),
                saved.openingDate(),
                saved.recordCode(),
                saved.openedAt(),
                saved.closedAt(),
                saved.statusId().value(),
                saved.createdAt(),
                saved.createdBy().value());
    }
}
