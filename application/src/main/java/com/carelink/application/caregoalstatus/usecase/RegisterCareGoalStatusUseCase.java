package com.carelink.application.caregoalstatus.usecase;

import com.carelink.application.caregoalstatus.command.RegisterCareGoalStatusCommand;
import com.carelink.application.caregoalstatus.dto.CareGoalStatusResponse;
import com.carelink.domain.caregoalstatus.port.repository.CareGoalStatusRepository;
import com.carelink.domain.caregoalstatus.model.aggregate.CareGoalStatus;

public class RegisterCareGoalStatusUseCase {
    private final CareGoalStatusRepository repository;

    public RegisterCareGoalStatusUseCase(CareGoalStatusRepository repository) {
        this.repository = repository;
    }

    public CareGoalStatusResponse execute(RegisterCareGoalStatusCommand command) {
        CareGoalStatus aggregate = CareGoalStatus.register(
                command.code(),
                command.label(),
                command.active());
        CareGoalStatus saved = repository.save(aggregate);
        return new CareGoalStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
