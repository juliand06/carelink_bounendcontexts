package com.carelink.application.urgencylevel.usecase;

import com.carelink.application.urgencylevel.command.RegisterUrgencyLevelCommand;
import com.carelink.application.urgencylevel.dto.UrgencyLevelResponse;
import com.carelink.domain.urgencylevel.port.repository.UrgencyLevelRepository;
import com.carelink.domain.urgencylevel.model.aggregate.UrgencyLevel;

public class RegisterUrgencyLevelUseCase {
    private final UrgencyLevelRepository repository;

    public RegisterUrgencyLevelUseCase(UrgencyLevelRepository repository) {
        this.repository = repository;
    }

    public UrgencyLevelResponse execute(RegisterUrgencyLevelCommand command) {
        UrgencyLevel aggregate = UrgencyLevel.register(
                command.code(),
                command.label(),
                command.active());
        UrgencyLevel saved = repository.save(aggregate);
        return new UrgencyLevelResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
