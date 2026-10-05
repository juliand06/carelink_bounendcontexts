package com.carelink.domain.urgencylevel.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.urgencylevel.event.UrgencyLevelRegisteredEvent;
import com.carelink.domain.urgencylevel.event.UrgencyLevelUpdatedEvent;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;

public class UrgencyLevel extends AggregateRoot {
    private final UrgencyLevelId id;
    private String code;
    private String label;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UrgencyLevel(
            UrgencyLevelId id,
            String code,
            String label,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.active = Objects.requireNonNull(active, "active must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static UrgencyLevel register(
            String code,
            String label,
            Boolean active) {
        UrgencyLevelId id = UrgencyLevelId.generate();
        LocalDateTime now = LocalDateTime.now();
        UrgencyLevel aggregate = new UrgencyLevel(
                id,
                code,
                label,
                active,
                now,
                now);
        aggregate.recordEvent(new UrgencyLevelRegisteredEvent(id, now));
        return aggregate;
    }

    public static UrgencyLevel restore(
            UrgencyLevelId id,
            String code,
            String label,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new UrgencyLevel(
                id,
                code,
                label,
                active,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String label,
            Boolean active) {
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.active = Objects.requireNonNull(active, "active must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new UrgencyLevelUpdatedEvent(
                this.id,
                this.code,
                this.label,
                this.active,
                now));
    }

    public UrgencyLevelId id() {
        return id;
    }

    public String code() {
        return code;
    }

    public String label() {
        return label;
    }

    public Boolean active() {
        return active;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
