package com.carelink.application.dangerlevel.usecase;

import java.time.LocalDateTime;

import com.carelink.application.dangerlevel.exception.DangerLevelNotFoundApplicationException;
import com.carelink.domain.dangerlevel.event.DangerLevelDeletedEvent;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;
import com.carelink.domain.dangerlevel.port.repository.DangerLevelRepository;

public class DeleteDangerLevelUseCase {
    private final DangerLevelRepository repository;

    public DeleteDangerLevelUseCase(DangerLevelRepository repository) {
        this.repository = repository;
    }

    public DangerLevelDeletedEvent execute(DangerLevelId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new DangerLevelNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new DangerLevelDeletedEvent(id, LocalDateTime.now());
    }
}
