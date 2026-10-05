package com.carelink.domain.handoff.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;

public record HandoffUpdatedEvent(
        HandoffId id,
        SupportConversationId conversationId,
        HandoffStatusId handoffStatusId,
        Boolean originatedFromAi,
        String handoffReason,
        LocalDateTime occurredOn
) implements DomainEvent {
    public HandoffUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(handoffStatusId, "handoffStatusId must not be null");
        Objects.requireNonNull(originatedFromAi, "originatedFromAi must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
