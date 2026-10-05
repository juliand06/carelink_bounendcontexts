package com.carelink.application.supportconversation.usecase;

import com.carelink.application.supportconversation.command.UpdateSupportConversationCommand;
import com.carelink.application.supportconversation.dto.SupportConversationResponse;
import com.carelink.domain.supportconversation.port.repository.SupportConversationRepository;
import com.carelink.application.supportconversation.exception.SupportConversationNotFoundApplicationException;

public class UpdateSupportConversationUseCase {
    private final SupportConversationRepository repository;

    public UpdateSupportConversationUseCase(SupportConversationRepository repository) {
        this.repository = repository;
    }

    public SupportConversationResponse execute(UpdateSupportConversationCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new SupportConversationNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.conversationStatusId(),
                command.urgencyLevelId(),
                command.lastInteractionAt(),
                command.isClosed(),
                command.closedAt(),
                command.closedBy());
        var saved = repository.save(aggregate);
        return new SupportConversationResponse(
                saved.id().value(),
                saved.conversationStatusId().value(),
                saved.urgencyLevelId().value(),
                saved.lastInteractionAt(),
                saved.isClosed(),
                saved.closedAt(),
                saved.closedBy(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
