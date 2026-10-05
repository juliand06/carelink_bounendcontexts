package com.carelink.application.handoff.usecase;

import java.util.List;

import com.carelink.application.handoff.dto.HandoffResponse;
import com.carelink.domain.handoff.port.repository.HandoffRepository;

public class ListHandoffUseCase {
    private final HandoffRepository repository;

    public ListHandoffUseCase(HandoffRepository repository) {
        this.repository = repository;
    }

    public List<HandoffResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new HandoffResponse(
                        aggregate.id().value(),
                        aggregate.conversationId().value(),
                        aggregate.handoffStatusId().value(),
                        aggregate.originatedFromAi(),
                        aggregate.handoffReason(),
                        aggregate.createdAt()))
                .toList();
    }
}
