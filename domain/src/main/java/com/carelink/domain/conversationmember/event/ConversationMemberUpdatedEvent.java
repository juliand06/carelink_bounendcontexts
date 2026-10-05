package com.carelink.domain.conversationmember.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;

public record ConversationMemberUpdatedEvent(
        ConversationMemberId id,
        SupportConversationId conversationId,
        ClientId clientId,
        SpecialistId specialistId,
        SenderRoleId senderRoleId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ConversationMemberUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(senderRoleId, "senderRoleId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
