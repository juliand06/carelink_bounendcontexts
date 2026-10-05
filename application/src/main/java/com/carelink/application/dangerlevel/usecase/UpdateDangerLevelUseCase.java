package com.carelink.application.dangerlevel.usecase;

import com.carelink.application.dangerlevel.command.UpdateDangerLevelCommand;
import com.carelink.application.dangerlevel.dto.DangerLevelResponse;
import com.carelink.domain.dangerlevel.port.repository.DangerLevelRepository;
import com.carelink.application.dangerlevel.exception.DangerLevelNotFoundApplicationException;

public class UpdateDangerLevelUseCase {
    private final DangerLevelRepository repository;

    public UpdateDangerLevelUseCase(DangerLevelRepository repository) {
        this.repository = repository;
    }

    public DangerLevelResponse execute(UpdateDangerLevelCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new DangerLevelNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.label(),
                command.active(),
                command.severityRank());
        var saved = repository.save(aggregate);
        return new DangerLevelResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.severityRank(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
