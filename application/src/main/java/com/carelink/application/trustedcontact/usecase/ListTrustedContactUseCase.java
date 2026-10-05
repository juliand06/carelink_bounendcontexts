package com.carelink.application.trustedcontact.usecase;

import java.util.List;

import com.carelink.application.trustedcontact.dto.TrustedContactResponse;
import com.carelink.domain.trustedcontact.port.repository.TrustedContactRepository;

public class ListTrustedContactUseCase {
    private final TrustedContactRepository repository;

    public ListTrustedContactUseCase(TrustedContactRepository repository) {
        this.repository = repository;
    }

    public List<TrustedContactResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new TrustedContactResponse(
                        aggregate.id().value(),
                        aggregate.fullName(),
                        aggregate.relationshipTypeId().value(),
                        aggregate.isPrimaryContact(),
                        aggregate.notes(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
