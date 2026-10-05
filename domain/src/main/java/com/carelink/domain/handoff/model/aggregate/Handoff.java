package com.carelink.domain.handoff.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.handoff.event.HandoffRegisteredEvent;
import com.carelink.domain.handoff.event.HandoffUpdatedEvent;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;

public class Handoff extends AggregateRoot {
    private final HandoffId id;
    private SupportConversationId conversationId;
    private HandoffStatusId handoffStatusId;
    private Boolean originatedFromAi;
    private String handoffReason;
    private final LocalDateTime createdAt;

    private Handoff(
            HandoffId id,
            SupportConversationId conversationId,
            HandoffStatusId handoffStatusId,
            Boolean originatedFromAi,
            String handoffReason,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.handoffStatusId = Objects.requireNonNull(handoffStatusId, "handoffStatusId must not be null");
        this.originatedFromAi = Objects.requireNonNull(originatedFromAi, "originatedFromAi must not be null");
        this.handoffReason = handoffReason;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
    }

    public static Handoff register(
            SupportConversationId conversationId,
            HandoffStatusId handoffStatusId,
            Boolean originatedFromAi,
            String handoffReason) {
        HandoffId id = HandoffId.generate();
        LocalDateTime now = LocalDateTime.now();
        Handoff aggregate = new Handoff(
                id,
                conversationId,
                handoffStatusId,
                originatedFromAi,
                handoffReason,
                now);
        aggregate.recordEvent(new HandoffRegisteredEvent(id, now));
        return aggregate;
    }

    public static Handoff restore(
            HandoffId id,
            SupportConversationId conversationId,
            HandoffStatusId handoffStatusId,
            Boolean originatedFromAi,
            String handoffReason,
            LocalDateTime createdAt) {
        return new Handoff(
                id,
                conversationId,
                handoffStatusId,
                originatedFromAi,
                handoffReason,
                createdAt);
    }

    public void update(
            SupportConversationId conversationId,
            HandoffStatusId handoffStatusId,
            Boolean originatedFromAi,
            String handoffReason) {
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.handoffStatusId = Objects.requireNonNull(handoffStatusId, "handoffStatusId must not be null");
        this.originatedFromAi = Objects.requireNonNull(originatedFromAi, "originatedFromAi must not be null");
        this.handoffReason = handoffReason;
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new HandoffUpdatedEvent(
                this.id,
                this.conversationId,
                this.handoffStatusId,
                this.originatedFromAi,
                this.handoffReason,
                now));
    }

    public HandoffId id() {
        return id;
    }

    public SupportConversationId conversationId() {
        return conversationId;
    }

    public HandoffStatusId handoffStatusId() {
        return handoffStatusId;
    }

    public Boolean originatedFromAi() {
        return originatedFromAi;
    }

    public String handoffReason() {
        return handoffReason;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
