package com.carelink.application.healthrecordstatus.usecase;

import com.carelink.application.healthrecordstatus.command.RegisterHealthRecordStatusCommand;
import com.carelink.application.healthrecordstatus.dto.HealthRecordStatusResponse;
import com.carelink.domain.healthrecordstatus.port.repository.HealthRecordStatusRepository;
import com.carelink.domain.healthrecordstatus.model.aggregate.HealthRecordStatus;

public class RegisterHealthRecordStatusUseCase {
    private final HealthRecordStatusRepository repository;

    public RegisterHealthRecordStatusUseCase(HealthRecordStatusRepository repository) {
        this.repository = repository;
    }

    public HealthRecordStatusResponse execute(RegisterHealthRecordStatusCommand command) {
        HealthRecordStatus aggregate = HealthRecordStatus.register(
                command.code(),
                command.label());
        HealthRecordStatus saved = repository.save(aggregate);
        return new HealthRecordStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
