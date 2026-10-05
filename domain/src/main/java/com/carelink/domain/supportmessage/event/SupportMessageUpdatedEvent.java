package com.carelink.domain.supportmessage.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;

public record SupportMessageUpdatedEvent(
        SupportMessageId id,
        SupportConversationId conversationId,
        MessageFormatId messageFormatId,
        ConversationMemberId memberId,
        String payload,
        String extraData,
        LocalDateTime occurredOn
) implements DomainEvent {
    public SupportMessageUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(messageFormatId, "messageFormatId must not be null");
        Objects.requireNonNull(memberId, "memberId must not be null");
        Objects.requireNonNull(payload, "payload must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
