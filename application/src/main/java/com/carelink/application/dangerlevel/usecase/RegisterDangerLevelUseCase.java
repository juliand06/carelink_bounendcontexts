package com.carelink.application.dangerlevel.usecase;

import com.carelink.application.dangerlevel.command.RegisterDangerLevelCommand;
import com.carelink.application.dangerlevel.dto.DangerLevelResponse;
import com.carelink.domain.dangerlevel.port.repository.DangerLevelRepository;
import com.carelink.domain.dangerlevel.model.aggregate.DangerLevel;

public class RegisterDangerLevelUseCase {
    private final DangerLevelRepository repository;

    public RegisterDangerLevelUseCase(DangerLevelRepository repository) {
        this.repository = repository;
    }

    public DangerLevelResponse execute(RegisterDangerLevelCommand command) {
        DangerLevel aggregate = DangerLevel.register(
                command.code(),
                command.label(),
                command.active(),
                command.severityRank());
        DangerLevel saved = repository.save(aggregate);
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
