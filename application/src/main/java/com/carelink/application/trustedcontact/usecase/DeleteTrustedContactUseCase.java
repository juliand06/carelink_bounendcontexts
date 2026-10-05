package com.carelink.application.trustedcontact.usecase;

import java.time.LocalDateTime;

import com.carelink.application.trustedcontact.exception.TrustedContactNotFoundApplicationException;
import com.carelink.domain.trustedcontact.event.TrustedContactDeletedEvent;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import com.carelink.domain.trustedcontact.port.repository.TrustedContactRepository;

public class DeleteTrustedContactUseCase {
    private final TrustedContactRepository repository;

    public DeleteTrustedContactUseCase(TrustedContactRepository repository) {
        this.repository = repository;
    }

    public TrustedContactDeletedEvent execute(TrustedContactId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new TrustedContactNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new TrustedContactDeletedEvent(id, LocalDateTime.now());
    }
}
