package com.carelink.application.caregoalstatus.usecase;

import java.time.LocalDateTime;

import com.carelink.application.caregoalstatus.exception.CareGoalStatusNotFoundApplicationException;
import com.carelink.domain.caregoalstatus.event.CareGoalStatusDeletedEvent;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.domain.caregoalstatus.port.repository.CareGoalStatusRepository;

public class DeleteCareGoalStatusUseCase {
    private final CareGoalStatusRepository repository;

    public DeleteCareGoalStatusUseCase(CareGoalStatusRepository repository) {
        this.repository = repository;
    }

    public CareGoalStatusDeletedEvent execute(CareGoalStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new CareGoalStatusNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new CareGoalStatusDeletedEvent(id, LocalDateTime.now());
    }
}
