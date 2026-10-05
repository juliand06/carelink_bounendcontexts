package com.carelink.domain.caregoalstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.caregoalstatus.event.CareGoalStatusRegisteredEvent;
import com.carelink.domain.caregoalstatus.event.CareGoalStatusUpdatedEvent;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.domain.common.model.AggregateRoot;

public class CareGoalStatus extends AggregateRoot {
    private final CareGoalStatusId id;
    private String code;
    private String label;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CareGoalStatus(
            CareGoalStatusId id,
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

    public static CareGoalStatus register(
            String code,
            String label,
            Boolean active) {
        CareGoalStatusId id = CareGoalStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        CareGoalStatus aggregate = new CareGoalStatus(
                id,
                code,
                label,
                active,
                now,
                now);
        aggregate.recordEvent(new CareGoalStatusRegisteredEvent(id, now));
        return aggregate;
    }

    public static CareGoalStatus restore(
            CareGoalStatusId id,
            String code,
            String label,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new CareGoalStatus(
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
        recordEvent(new CareGoalStatusUpdatedEvent(
                this.id,
                this.code,
                this.label,
                this.active,
                now));
    }

    public CareGoalStatusId id() {
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
