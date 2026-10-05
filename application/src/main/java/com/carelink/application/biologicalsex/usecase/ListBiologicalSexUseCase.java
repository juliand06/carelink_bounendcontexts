package com.carelink.application.biologicalsex.usecase;

import java.util.List;

import com.carelink.application.biologicalsex.dto.BiologicalSexResponse;
import com.carelink.domain.biologicalsex.port.repository.BiologicalSexRepository;

public class ListBiologicalSexUseCase {
    private final BiologicalSexRepository repository;

    public ListBiologicalSexUseCase(BiologicalSexRepository repository) {
        this.repository = repository;
    }

    public List<BiologicalSexResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new BiologicalSexResponse(
                        aggregate.id().value(),
                        aggregate.label(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
