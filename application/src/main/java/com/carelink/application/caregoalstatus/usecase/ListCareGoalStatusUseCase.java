package com.carelink.application.caregoalstatus.usecase;

import java.util.List;

import com.carelink.application.caregoalstatus.dto.CareGoalStatusResponse;
import com.carelink.domain.caregoalstatus.port.repository.CareGoalStatusRepository;

public class ListCareGoalStatusUseCase {
    private final CareGoalStatusRepository repository;

    public ListCareGoalStatusUseCase(CareGoalStatusRepository repository) {
        this.repository = repository;
    }

    public List<CareGoalStatusResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new CareGoalStatusResponse(
                        aggregate.id().value(),
                        aggregate.code(),
                        aggregate.label(),
                        aggregate.active(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
