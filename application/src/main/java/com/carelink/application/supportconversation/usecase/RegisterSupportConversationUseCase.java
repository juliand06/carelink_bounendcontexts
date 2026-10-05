package com.carelink.application.supportconversation.usecase;

import com.carelink.application.supportconversation.command.RegisterSupportConversationCommand;
import com.carelink.application.supportconversation.dto.SupportConversationResponse;
import com.carelink.domain.supportconversation.port.repository.SupportConversationRepository;
import com.carelink.domain.supportconversation.model.aggregate.SupportConversation;

public class RegisterSupportConversationUseCase {
    private final SupportConversationRepository repository;

    public RegisterSupportConversationUseCase(SupportConversationRepository repository) {
        this.repository = repository;
    }

    public SupportConversationResponse execute(RegisterSupportConversationCommand command) {
        SupportConversation aggregate = SupportConversation.register(
                command.conversationStatusId(),
                command.urgencyLevelId(),
                command.lastInteractionAt(),
                command.isClosed(),
                command.closedAt(),
                command.closedBy());
        SupportConversation saved = repository.save(aggregate);
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
