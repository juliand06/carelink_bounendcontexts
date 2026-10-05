package com.carelink.application.caregoal.usecase;

import java.util.List;

import com.carelink.application.caregoal.dto.CareGoalResponse;
import com.carelink.domain.caregoal.port.repository.CareGoalRepository;

public class ListCareGoalUseCase {
    private final CareGoalRepository repository;

    public ListCareGoalUseCase(CareGoalRepository repository) {
        this.repository = repository;
    }

    public List<CareGoalResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new CareGoalResponse(
                        aggregate.id().value(),
                        aggregate.carePlanId().value(),
                        aggregate.goalDescription(),
                        aggregate.targetDate(),
                        aggregate.achievedAt(),
                        aggregate.progressNotes(),
                        aggregate.careGoalStatusId().value(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
