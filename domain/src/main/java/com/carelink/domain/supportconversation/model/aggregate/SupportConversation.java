package com.carelink.domain.supportconversation.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.supportconversation.event.SupportConversationRegisteredEvent;
import com.carelink.domain.supportconversation.event.SupportConversationUpdatedEvent;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;

public class SupportConversation extends AggregateRoot {
    private final SupportConversationId id;
    private SupportConversationStatusId conversationStatusId;
    private UrgencyLevelId urgencyLevelId;
    private LocalDateTime lastInteractionAt;
    private Boolean isClosed;
    private LocalDateTime closedAt;
    private UUID closedBy;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private SupportConversation(
            SupportConversationId id,
            SupportConversationStatusId conversationStatusId,
            UrgencyLevelId urgencyLevelId,
            LocalDateTime lastInteractionAt,
            Boolean isClosed,
            LocalDateTime closedAt,
            UUID closedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationStatusId = Objects.requireNonNull(conversationStatusId, "conversationStatusId must not be null");
        this.urgencyLevelId = Objects.requireNonNull(urgencyLevelId, "urgencyLevelId must not be null");
        this.lastInteractionAt = lastInteractionAt;
        this.isClosed = Objects.requireNonNull(isClosed, "isClosed must not be null");
        this.closedAt = closedAt;
        this.closedBy = closedBy;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static SupportConversation register(
            SupportConversationStatusId conversationStatusId,
            UrgencyLevelId urgencyLevelId,
            LocalDateTime lastInteractionAt,
            Boolean isClosed,
            LocalDateTime closedAt,
            UUID closedBy) {
        SupportConversationId id = SupportConversationId.generate();
        LocalDateTime now = LocalDateTime.now();
        SupportConversation aggregate = new SupportConversation(
                id,
                conversationStatusId,
                urgencyLevelId,
                lastInteractionAt,
                isClosed,
                closedAt,
                closedBy,
                now,
                now);
        aggregate.recordEvent(new SupportConversationRegisteredEvent(id, now));
        return aggregate;
    }

    public static SupportConversation restore(
            SupportConversationId id,
            SupportConversationStatusId conversationStatusId,
            UrgencyLevelId urgencyLevelId,
            LocalDateTime lastInteractionAt,
            Boolean isClosed,
            LocalDateTime closedAt,
            UUID closedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new SupportConversation(
                id,
                conversationStatusId,
                urgencyLevelId,
                lastInteractionAt,
                isClosed,
                closedAt,
                closedBy,
                createdAt,
                updatedAt);
    }

    public void update(
            SupportConversationStatusId conversationStatusId,
            UrgencyLevelId urgencyLevelId,
            LocalDateTime lastInteractionAt,
            Boolean isClosed,
            LocalDateTime closedAt,
            UUID closedBy) {
        this.conversationStatusId = Objects.requireNonNull(conversationStatusId, "conversationStatusId must not be null");
        this.urgencyLevelId = Objects.requireNonNull(urgencyLevelId, "urgencyLevelId must not be null");
        this.lastInteractionAt = lastInteractionAt;
        this.isClosed = Objects.requireNonNull(isClosed, "isClosed must not be null");
        this.closedAt = closedAt;
        this.closedBy = closedBy;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new SupportConversationUpdatedEvent(
                this.id,
                this.conversationStatusId,
                this.urgencyLevelId,
                this.lastInteractionAt,
                this.isClosed,
                this.closedAt,
                this.closedBy,
                now));
    }

    public SupportConversationId id() {
        return id;
    }

    public SupportConversationStatusId conversationStatusId() {
        return conversationStatusId;
    }

    public UrgencyLevelId urgencyLevelId() {
        return urgencyLevelId;
    }

    public LocalDateTime lastInteractionAt() {
        return lastInteractionAt;
    }

    public Boolean isClosed() {
        return isClosed;
    }

    public LocalDateTime closedAt() {
        return closedAt;
    }

    public UUID closedBy() {
        return closedBy;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
