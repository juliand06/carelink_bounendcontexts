package com.carelink.application.informedconsenttype.usecase;

import java.util.List;

import com.carelink.application.informedconsenttype.dto.InformedConsentTypeResponse;
import com.carelink.domain.informedconsenttype.port.repository.InformedConsentTypeRepository;

public class ListInformedConsentTypeUseCase {
    private final InformedConsentTypeRepository repository;

    public ListInformedConsentTypeUseCase(InformedConsentTypeRepository repository) {
        this.repository = repository;
    }

    public List<InformedConsentTypeResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new InformedConsentTypeResponse(
                        aggregate.id().value(),
                        aggregate.code(),
                        aggregate.label(),
                        aggregate.active(),
                        aggregate.description(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
