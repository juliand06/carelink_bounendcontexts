package com.carelink.application.biologicalsex.usecase;

import java.time.LocalDateTime;

import com.carelink.application.biologicalsex.exception.BiologicalSexNotFoundApplicationException;
import com.carelink.domain.biologicalsex.event.BiologicalSexDeletedEvent;
import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;
import com.carelink.domain.biologicalsex.port.repository.BiologicalSexRepository;

public class DeleteBiologicalSexUseCase {
    private final BiologicalSexRepository repository;

    public DeleteBiologicalSexUseCase(BiologicalSexRepository repository) {
        this.repository = repository;
    }

    public BiologicalSexDeletedEvent execute(BiologicalSexId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new BiologicalSexNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new BiologicalSexDeletedEvent(id, LocalDateTime.now());
    }
}
