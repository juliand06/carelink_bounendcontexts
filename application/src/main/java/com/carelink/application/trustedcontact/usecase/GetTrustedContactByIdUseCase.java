package com.carelink.application.trustedcontact.usecase;

import com.carelink.application.trustedcontact.dto.TrustedContactResponse;
import com.carelink.domain.trustedcontact.port.repository.TrustedContactRepository;
import com.carelink.application.trustedcontact.exception.TrustedContactNotFoundApplicationException;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public class GetTrustedContactByIdUseCase {
    private final TrustedContactRepository repository;

    public GetTrustedContactByIdUseCase(TrustedContactRepository repository) {
        this.repository = repository;
    }

    public TrustedContactResponse execute(TrustedContactId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new TrustedContactNotFoundApplicationException(id.value().toString()));
        return new TrustedContactResponse(
                aggregate.id().value(),
                aggregate.fullName(),
                aggregate.relationshipTypeId().value(),
                aggregate.isPrimaryContact(),
                aggregate.notes(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
