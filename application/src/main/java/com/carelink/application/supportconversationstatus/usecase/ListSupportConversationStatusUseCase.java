package com.carelink.application.supportconversationstatus.usecase;

import java.util.List;

import com.carelink.application.supportconversationstatus.dto.SupportConversationStatusResponse;
import com.carelink.domain.supportconversationstatus.port.repository.SupportConversationStatusRepository;

public class ListSupportConversationStatusUseCase {
    private final SupportConversationStatusRepository repository;

    public ListSupportConversationStatusUseCase(SupportConversationStatusRepository repository) {
        this.repository = repository;
    }

    public List<SupportConversationStatusResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new SupportConversationStatusResponse(
                        aggregate.id().value(),
                        aggregate.code(),
                        aggregate.label(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
