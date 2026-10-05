package com.carelink.application.specialistcredential.usecase;

import java.time.LocalDateTime;

import com.carelink.application.specialistcredential.exception.SpecialistCredentialNotFoundApplicationException;
import com.carelink.domain.specialistcredential.event.SpecialistCredentialDeletedEvent;
import com.carelink.domain.specialistcredential.model.valueobject.SpecialistCredentialId;
import com.carelink.domain.specialistcredential.port.repository.SpecialistCredentialRepository;

public class DeleteSpecialistCredentialUseCase {
    private final SpecialistCredentialRepository repository;

    public DeleteSpecialistCredentialUseCase(SpecialistCredentialRepository repository) {
        this.repository = repository;
    }

    public SpecialistCredentialDeletedEvent execute(SpecialistCredentialId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SpecialistCredentialNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new SpecialistCredentialDeletedEvent(id, LocalDateTime.now());
    }
}
