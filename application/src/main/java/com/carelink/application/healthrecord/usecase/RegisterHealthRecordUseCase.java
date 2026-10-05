package com.carelink.application.healthrecord.usecase;

import com.carelink.application.healthrecord.command.RegisterHealthRecordCommand;
import com.carelink.application.healthrecord.dto.HealthRecordResponse;
import com.carelink.domain.healthrecord.port.repository.HealthRecordRepository;
import com.carelink.domain.healthrecord.model.aggregate.HealthRecord;

public class RegisterHealthRecordUseCase {
    private final HealthRecordRepository repository;

    public RegisterHealthRecordUseCase(HealthRecordRepository repository) {
        this.repository = repository;
    }

    public HealthRecordResponse execute(RegisterHealthRecordCommand command) {
        HealthRecord aggregate = HealthRecord.register(
                command.clientId(),
                command.recordCode(),
                command.openedAt(),
                command.closedAt(),
                command.statusId(),
                command.createdBy());
        HealthRecord saved = repository.save(aggregate);
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
