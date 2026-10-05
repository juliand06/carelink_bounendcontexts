package com.carelink.application.iddocumenttype.usecase;

import java.util.List;

import com.carelink.application.iddocumenttype.dto.IdDocumentTypeResponse;
import com.carelink.domain.iddocumenttype.port.repository.IdDocumentTypeRepository;

public class ListIdDocumentTypeUseCase {
    private final IdDocumentTypeRepository repository;

    public ListIdDocumentTypeUseCase(IdDocumentTypeRepository repository) {
        this.repository = repository;
    }

    public List<IdDocumentTypeResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new IdDocumentTypeResponse(
                        aggregate.id().value(),
                        aggregate.code(),
                        aggregate.label(),
                        aggregate.active(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
