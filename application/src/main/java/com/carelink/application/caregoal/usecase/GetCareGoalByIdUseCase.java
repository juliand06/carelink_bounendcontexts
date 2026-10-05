package com.carelink.application.caregoal.usecase;

import com.carelink.application.caregoal.dto.CareGoalResponse;
import com.carelink.domain.caregoal.port.repository.CareGoalRepository;
import com.carelink.application.caregoal.exception.CareGoalNotFoundApplicationException;
import com.carelink.domain.caregoal.model.valueobject.CareGoalId;

public class GetCareGoalByIdUseCase {
    private final CareGoalRepository repository;

    public GetCareGoalByIdUseCase(CareGoalRepository repository) {
        this.repository = repository;
    }

    public CareGoalResponse execute(CareGoalId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new CareGoalNotFoundApplicationException(id.value().toString()));
        return new CareGoalResponse(
                aggregate.id().value(),
                aggregate.carePlanId().value(),
                aggregate.goalDescription(),
                aggregate.targetDate(),
                aggregate.achievedAt(),
                aggregate.progressNotes(),
                aggregate.careGoalStatusId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
