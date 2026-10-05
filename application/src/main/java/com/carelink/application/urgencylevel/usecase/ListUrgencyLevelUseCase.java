package com.carelink.application.urgencylevel.usecase;

import java.util.List;

import com.carelink.application.urgencylevel.dto.UrgencyLevelResponse;
import com.carelink.domain.urgencylevel.port.repository.UrgencyLevelRepository;

public class ListUrgencyLevelUseCase {
    private final UrgencyLevelRepository repository;

    public ListUrgencyLevelUseCase(UrgencyLevelRepository repository) {
        this.repository = repository;
    }

    public List<UrgencyLevelResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new UrgencyLevelResponse(
                        aggregate.id().value(),
                        aggregate.code(),
                        aggregate.label(),
                        aggregate.active(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
