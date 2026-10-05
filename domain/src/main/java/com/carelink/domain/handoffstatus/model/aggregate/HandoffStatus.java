package com.carelink.domain.handoffstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.handoffstatus.event.HandoffStatusRegisteredEvent;
import com.carelink.domain.handoffstatus.event.HandoffStatusUpdatedEvent;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;

public class HandoffStatus extends AggregateRoot {
    private final HandoffStatusId id;
    private String label;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private HandoffStatus(
            HandoffStatusId id,
            String label,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static HandoffStatus register(
            String label) {
        HandoffStatusId id = HandoffStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        HandoffStatus aggregate = new HandoffStatus(
                id,
                label,
                now,
                now);
        aggregate.recordEvent(new HandoffStatusRegisteredEvent(id, now));
        return aggregate;
    }

    public static HandoffStatus restore(
            HandoffStatusId id,
            String label,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new HandoffStatus(
                id,
                label,
                createdAt,
                updatedAt);
    }

    public void update(
            String label) {
        this.label = Objects.requireNonNull(label, "label must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new HandoffStatusUpdatedEvent(
                this.id,
                this.label,
                now));
    }

    public HandoffStatusId id() {
        return id;
    }

    public String label() {
        return label;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
