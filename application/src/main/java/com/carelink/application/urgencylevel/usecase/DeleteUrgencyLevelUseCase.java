package com.carelink.application.urgencylevel.usecase;

import java.time.LocalDateTime;

import com.carelink.application.urgencylevel.exception.UrgencyLevelNotFoundApplicationException;
import com.carelink.domain.urgencylevel.event.UrgencyLevelDeletedEvent;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;
import com.carelink.domain.urgencylevel.port.repository.UrgencyLevelRepository;

public class DeleteUrgencyLevelUseCase {
    private final UrgencyLevelRepository repository;

    public DeleteUrgencyLevelUseCase(UrgencyLevelRepository repository) {
        this.repository = repository;
    }

    public UrgencyLevelDeletedEvent execute(UrgencyLevelId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new UrgencyLevelNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new UrgencyLevelDeletedEvent(id, LocalDateTime.now());
    }
}
