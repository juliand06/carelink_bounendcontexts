package com.carelink.application.supportconversation.usecase;

import com.carelink.application.supportconversation.dto.SupportConversationResponse;
import com.carelink.domain.supportconversation.port.repository.SupportConversationRepository;
import com.carelink.application.supportconversation.exception.SupportConversationNotFoundApplicationException;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;

public class GetSupportConversationByIdUseCase {
    private final SupportConversationRepository repository;

    public GetSupportConversationByIdUseCase(SupportConversationRepository repository) {
        this.repository = repository;
    }

    public SupportConversationResponse execute(SupportConversationId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SupportConversationNotFoundApplicationException(id.value().toString()));
        return new SupportConversationResponse(
                aggregate.id().value(),
                aggregate.conversationStatusId().value(),
                aggregate.urgencyLevelId().value(),
                aggregate.lastInteractionAt(),
                aggregate.isClosed(),
                aggregate.closedAt(),
                aggregate.closedBy(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
