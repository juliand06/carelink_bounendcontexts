package com.carelink.application.conversationmember.usecase;

import com.carelink.application.conversationmember.dto.ConversationMemberResponse;
import com.carelink.domain.conversationmember.port.repository.ConversationMemberRepository;
import com.carelink.application.conversationmember.exception.ConversationMemberNotFoundApplicationException;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;

public class GetConversationMemberByIdUseCase {
    private final ConversationMemberRepository repository;

    public GetConversationMemberByIdUseCase(ConversationMemberRepository repository) {
        this.repository = repository;
    }

    public ConversationMemberResponse execute(ConversationMemberId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ConversationMemberNotFoundApplicationException(id.value().toString()));
        return new ConversationMemberResponse(
                aggregate.id().value(),
                aggregate.conversationId().value(),
                aggregate.clientId() == null ? null : aggregate.clientId().value(),
                aggregate.specialistId() == null ? null : aggregate.specialistId().value(),
                aggregate.senderRoleId().value(),
                aggregate.joinedAt());
    }
}
