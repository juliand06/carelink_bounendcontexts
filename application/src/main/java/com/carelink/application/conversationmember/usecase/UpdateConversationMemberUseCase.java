package com.carelink.application.conversationmember.usecase;

import com.carelink.application.conversationmember.command.UpdateConversationMemberCommand;
import com.carelink.application.conversationmember.dto.ConversationMemberResponse;
import com.carelink.domain.conversationmember.port.repository.ConversationMemberRepository;
import com.carelink.application.conversationmember.exception.ConversationMemberNotFoundApplicationException;

public class UpdateConversationMemberUseCase {
    private final ConversationMemberRepository repository;

    public UpdateConversationMemberUseCase(ConversationMemberRepository repository) {
        this.repository = repository;
    }

    public ConversationMemberResponse execute(UpdateConversationMemberCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ConversationMemberNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.conversationId(),
                command.clientId(),
                command.specialistId(),
                command.senderRoleId());
        var saved = repository.save(aggregate);
        return new ConversationMemberResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.clientId() == null ? null : saved.clientId().value(),
                saved.specialistId() == null ? null : saved.specialistId().value(),
                saved.senderRoleId().value(),
                saved.joinedAt());
    }
}
