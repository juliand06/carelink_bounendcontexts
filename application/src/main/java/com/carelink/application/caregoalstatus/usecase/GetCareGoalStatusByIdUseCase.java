package com.carelink.application.caregoalstatus.usecase;

import com.carelink.application.caregoalstatus.dto.CareGoalStatusResponse;
import com.carelink.domain.caregoalstatus.port.repository.CareGoalStatusRepository;
import com.carelink.application.caregoalstatus.exception.CareGoalStatusNotFoundApplicationException;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;

public class GetCareGoalStatusByIdUseCase {
    private final CareGoalStatusRepository repository;

    public GetCareGoalStatusByIdUseCase(CareGoalStatusRepository repository) {
        this.repository = repository;
    }

    public CareGoalStatusResponse execute(CareGoalStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new CareGoalStatusNotFoundApplicationException(id.value().toString()));
        return new CareGoalStatusResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.label(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
