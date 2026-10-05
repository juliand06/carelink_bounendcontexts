package com.carelink.domain.conversationmember.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;

public record ConversationMemberDeletedEvent(
        ConversationMemberId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ConversationMemberDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
