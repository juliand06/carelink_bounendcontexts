package com.carelink.domain.senderrole.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.senderrole.event.SenderRoleRegisteredEvent;
import com.carelink.domain.senderrole.event.SenderRoleUpdatedEvent;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;

public class SenderRole extends AggregateRoot {
    private final SenderRoleId id;
    private String label;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private SenderRole(
            SenderRoleId id,
            String label,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static SenderRole register(
            String label) {
        SenderRoleId id = SenderRoleId.generate();
        LocalDateTime now = LocalDateTime.now();
        SenderRole aggregate = new SenderRole(
                id,
                label,
                now,
                now);
        aggregate.recordEvent(new SenderRoleRegisteredEvent(id, now));
        return aggregate;
    }

    public static SenderRole restore(
            SenderRoleId id,
            String label,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new SenderRole(
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
        recordEvent(new SenderRoleUpdatedEvent(
                this.id,
                this.label,
                now));
    }

    public SenderRoleId id() {
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
