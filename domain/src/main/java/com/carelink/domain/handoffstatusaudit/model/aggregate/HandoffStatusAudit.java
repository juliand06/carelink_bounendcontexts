package com.carelink.domain.handoffstatusaudit.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.handoffstatusaudit.event.HandoffStatusAuditRegisteredEvent;
import com.carelink.domain.handoffstatusaudit.event.HandoffStatusAuditUpdatedEvent;
import com.carelink.domain.handoffstatusaudit.model.valueobject.HandoffStatusAuditId;

public class HandoffStatusAudit extends AggregateRoot {
    private final HandoffStatusAuditId id;
    private HandoffId handoffId;
    private HandoffStatusId handoffStatusId;
    private final LocalDateTime recordedAt;
    private final LocalDateTime transitionedAt;

    private HandoffStatusAudit(
            HandoffStatusAuditId id,
            HandoffId handoffId,
            HandoffStatusId handoffStatusId,
            LocalDateTime recordedAt,
            LocalDateTime transitionedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.handoffId = Objects.requireNonNull(handoffId, "handoffId must not be null");
        this.handoffStatusId = Objects.requireNonNull(handoffStatusId, "handoffStatusId must not be null");
        this.recordedAt = Objects.requireNonNull(recordedAt, "recordedAt must not be null");
        this.transitionedAt = Objects.requireNonNull(transitionedAt, "transitionedAt must not be null");
    }

    public static HandoffStatusAudit register(
            HandoffId handoffId,
            HandoffStatusId handoffStatusId) {
        HandoffStatusAuditId id = HandoffStatusAuditId.generate();
        LocalDateTime now = LocalDateTime.now();
        HandoffStatusAudit aggregate = new HandoffStatusAudit(
                id,
                handoffId,
                handoffStatusId,
                now,
                now);
        aggregate.recordEvent(new HandoffStatusAuditRegisteredEvent(id, now));
        return aggregate;
    }

    public static HandoffStatusAudit restore(
            HandoffStatusAuditId id,
            HandoffId handoffId,
            HandoffStatusId handoffStatusId,
            LocalDateTime recordedAt,
            LocalDateTime transitionedAt) {
        return new HandoffStatusAudit(
                id,
                handoffId,
                handoffStatusId,
                recordedAt,
                transitionedAt);
    }

    public void update(
            HandoffId handoffId,
            HandoffStatusId handoffStatusId) {
        this.handoffId = Objects.requireNonNull(handoffId, "handoffId must not be null");
        this.handoffStatusId = Objects.requireNonNull(handoffStatusId, "handoffStatusId must not be null");
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new HandoffStatusAuditUpdatedEvent(
                this.id,
                this.handoffId,
                this.handoffStatusId,
                now));
    }

    public HandoffStatusAuditId id() {
        return id;
    }

    public HandoffId handoffId() {
        return handoffId;
    }

    public HandoffStatusId handoffStatusId() {
        return handoffStatusId;
    }

    public LocalDateTime recordedAt() {
        return recordedAt;
    }

    public LocalDateTime transitionedAt() {
        return transitionedAt;
    }
}
