package com.carelink.application.municipality.usecase;

import java.time.LocalDateTime;

import com.carelink.application.municipality.exception.MunicipalityNotFoundApplicationException;
import com.carelink.domain.municipality.event.MunicipalityDeletedEvent;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.municipality.port.repository.MunicipalityRepository;

public class DeleteMunicipalityUseCase {
    private final MunicipalityRepository repository;

    public DeleteMunicipalityUseCase(MunicipalityRepository repository) {
        this.repository = repository;
    }

    public MunicipalityDeletedEvent execute(MunicipalityId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new MunicipalityNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new MunicipalityDeletedEvent(id, LocalDateTime.now());
    }
}
