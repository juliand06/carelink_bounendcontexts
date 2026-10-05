package com.carelink.application.caregoal.usecase;

import java.time.LocalDateTime;

import com.carelink.application.caregoal.exception.CareGoalNotFoundApplicationException;
import com.carelink.domain.caregoal.event.CareGoalDeletedEvent;
import com.carelink.domain.caregoal.model.valueobject.CareGoalId;
import com.carelink.domain.caregoal.port.repository.CareGoalRepository;

public class DeleteCareGoalUseCase {
    private final CareGoalRepository repository;

    public DeleteCareGoalUseCase(CareGoalRepository repository) {
        this.repository = repository;
    }

    public CareGoalDeletedEvent execute(CareGoalId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new CareGoalNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new CareGoalDeletedEvent(id, LocalDateTime.now());
    }
}
