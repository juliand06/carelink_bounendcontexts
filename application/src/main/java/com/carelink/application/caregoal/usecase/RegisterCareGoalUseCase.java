package com.carelink.application.caregoal.usecase;

import com.carelink.application.caregoal.command.RegisterCareGoalCommand;
import com.carelink.application.caregoal.dto.CareGoalResponse;
import com.carelink.domain.caregoal.port.repository.CareGoalRepository;
import com.carelink.domain.caregoal.model.aggregate.CareGoal;

public class RegisterCareGoalUseCase {
    private final CareGoalRepository repository;

    public RegisterCareGoalUseCase(CareGoalRepository repository) {
        this.repository = repository;
    }

    public CareGoalResponse execute(RegisterCareGoalCommand command) {
        CareGoal aggregate = CareGoal.register(
                command.carePlanId(),
                command.goalDescription(),
                command.targetDate(),
                command.achievedAt(),
                command.progressNotes(),
                command.careGoalStatusId());
        CareGoal saved = repository.save(aggregate);
        return new CareGoalResponse(
                saved.id().value(),
                saved.carePlanId().value(),
                saved.goalDescription(),
                saved.targetDate(),
                saved.achievedAt(),
                saved.progressNotes(),
                saved.careGoalStatusId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
