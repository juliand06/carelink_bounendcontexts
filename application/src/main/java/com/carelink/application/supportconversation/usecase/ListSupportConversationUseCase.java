package com.carelink.application.supportconversation.usecase;

import java.util.List;

import com.carelink.application.supportconversation.dto.SupportConversationResponse;
import com.carelink.domain.supportconversation.port.repository.SupportConversationRepository;

public class ListSupportConversationUseCase {
    private final SupportConversationRepository repository;

    public ListSupportConversationUseCase(SupportConversationRepository repository) {
        this.repository = repository;
    }

    public List<SupportConversationResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new SupportConversationResponse(
                        aggregate.id().value(),
                        aggregate.conversationStatusId().value(),
                        aggregate.urgencyLevelId().value(),
                        aggregate.lastInteractionAt(),
                        aggregate.isClosed(),
                        aggregate.closedAt(),
                        aggregate.closedBy(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
