package com.carelink.application.conversationmember.usecase;

import java.time.LocalDateTime;

import com.carelink.application.conversationmember.exception.ConversationMemberNotFoundApplicationException;
import com.carelink.domain.conversationmember.event.ConversationMemberDeletedEvent;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.conversationmember.port.repository.ConversationMemberRepository;

public class DeleteConversationMemberUseCase {
    private final ConversationMemberRepository repository;

    public DeleteConversationMemberUseCase(ConversationMemberRepository repository) {
        this.repository = repository;
    }

    public ConversationMemberDeletedEvent execute(ConversationMemberId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ConversationMemberNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ConversationMemberDeletedEvent(id, LocalDateTime.now());
    }
}
