package com.carelink.application.urgencylevel.usecase;

import com.carelink.application.urgencylevel.command.UpdateUrgencyLevelCommand;
import com.carelink.application.urgencylevel.dto.UrgencyLevelResponse;
import com.carelink.domain.urgencylevel.port.repository.UrgencyLevelRepository;
import com.carelink.application.urgencylevel.exception.UrgencyLevelNotFoundApplicationException;

public class UpdateUrgencyLevelUseCase {
    private final UrgencyLevelRepository repository;

    public UpdateUrgencyLevelUseCase(UrgencyLevelRepository repository) {
        this.repository = repository;
    }

    public UrgencyLevelResponse execute(UpdateUrgencyLevelCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new UrgencyLevelNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.label(),
                command.active());
        var saved = repository.save(aggregate);
        return new UrgencyLevelResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
