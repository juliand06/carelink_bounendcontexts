package com.carelink.application.classificationsystem.usecase;

import java.util.List;

import com.carelink.application.classificationsystem.dto.ClassificationSystemResponse;
import com.carelink.domain.classificationsystem.port.repository.ClassificationSystemRepository;

public class ListClassificationSystemUseCase {
    private final ClassificationSystemRepository repository;

    public ListClassificationSystemUseCase(ClassificationSystemRepository repository) {
        this.repository = repository;
    }

    public List<ClassificationSystemResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ClassificationSystemResponse(
                        aggregate.id().value(),
                        aggregate.code(),
                        aggregate.label(),
                        aggregate.active(),
                        aggregate.edition(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
