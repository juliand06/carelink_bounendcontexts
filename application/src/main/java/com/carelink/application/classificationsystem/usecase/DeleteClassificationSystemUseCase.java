package com.carelink.application.classificationsystem.usecase;

import java.time.LocalDateTime;

import com.carelink.application.classificationsystem.exception.ClassificationSystemNotFoundApplicationException;
import com.carelink.domain.classificationsystem.event.ClassificationSystemDeletedEvent;
import com.carelink.domain.classificationsystem.model.valueobject.ClassificationSystemId;
import com.carelink.domain.classificationsystem.port.repository.ClassificationSystemRepository;

public class DeleteClassificationSystemUseCase {
    private final ClassificationSystemRepository repository;

    public DeleteClassificationSystemUseCase(ClassificationSystemRepository repository) {
        this.repository = repository;
    }

    public ClassificationSystemDeletedEvent execute(ClassificationSystemId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClassificationSystemNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ClassificationSystemDeletedEvent(id, LocalDateTime.now());
    }
}
