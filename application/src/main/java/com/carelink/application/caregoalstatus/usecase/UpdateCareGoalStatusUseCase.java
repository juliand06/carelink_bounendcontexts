package com.carelink.application.caregoalstatus.usecase;

import com.carelink.application.caregoalstatus.command.UpdateCareGoalStatusCommand;
import com.carelink.application.caregoalstatus.dto.CareGoalStatusResponse;
import com.carelink.domain.caregoalstatus.port.repository.CareGoalStatusRepository;
import com.carelink.application.caregoalstatus.exception.CareGoalStatusNotFoundApplicationException;

public class UpdateCareGoalStatusUseCase {
    private final CareGoalStatusRepository repository;

    public UpdateCareGoalStatusUseCase(CareGoalStatusRepository repository) {
        this.repository = repository;
    }

    public CareGoalStatusResponse execute(UpdateCareGoalStatusCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new CareGoalStatusNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.label(),
                command.active());
        var saved = repository.save(aggregate);
        return new CareGoalStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
