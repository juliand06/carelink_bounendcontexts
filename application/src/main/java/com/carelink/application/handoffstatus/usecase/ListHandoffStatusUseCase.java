package com.carelink.application.handoffstatus.usecase;

import java.util.List;

import com.carelink.application.handoffstatus.dto.HandoffStatusResponse;
import com.carelink.domain.handoffstatus.port.repository.HandoffStatusRepository;

public class ListHandoffStatusUseCase {
    private final HandoffStatusRepository repository;

    public ListHandoffStatusUseCase(HandoffStatusRepository repository) {
        this.repository = repository;
    }

    public List<HandoffStatusResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new HandoffStatusResponse(
                        aggregate.id().value(),
                        aggregate.label(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
