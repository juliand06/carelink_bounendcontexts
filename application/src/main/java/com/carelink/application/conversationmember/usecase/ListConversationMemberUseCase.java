package com.carelink.application.conversationmember.usecase;

import java.util.List;

import com.carelink.application.conversationmember.dto.ConversationMemberResponse;
import com.carelink.domain.conversationmember.port.repository.ConversationMemberRepository;

public class ListConversationMemberUseCase {
    private final ConversationMemberRepository repository;

    public ListConversationMemberUseCase(ConversationMemberRepository repository) {
        this.repository = repository;
    }

    public List<ConversationMemberResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ConversationMemberResponse(
                        aggregate.id().value(),
                        aggregate.conversationId().value(),
                        aggregate.clientId() == null ? null : aggregate.clientId().value(),
                        aggregate.specialistId() == null ? null : aggregate.specialistId().value(),
                        aggregate.senderRoleId().value(),
                        aggregate.joinedAt()))
                .toList();
    }
}
