package com.carelink.domain.dangerlevel.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.dangerlevel.event.DangerLevelRegisteredEvent;
import com.carelink.domain.dangerlevel.event.DangerLevelUpdatedEvent;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;

public class DangerLevel extends AggregateRoot {
    private final DangerLevelId id;
    private String code;
    private String label;
    private Boolean active;
    private Integer severityRank;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private DangerLevel(
            DangerLevelId id,
            String code,
            String label,
            Boolean active,
            Integer severityRank,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.active = Objects.requireNonNull(active, "active must not be null");
        this.severityRank = Objects.requireNonNull(severityRank, "severityRank must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static DangerLevel register(
            String code,
            String label,
            Boolean active,
            Integer severityRank) {
        DangerLevelId id = DangerLevelId.generate();
        LocalDateTime now = LocalDateTime.now();
        DangerLevel aggregate = new DangerLevel(
                id,
                code,
                label,
                active,
                severityRank,
                now,
                now);
        aggregate.recordEvent(new DangerLevelRegisteredEvent(id, now));
        return aggregate;
    }

    public static DangerLevel restore(
            DangerLevelId id,
            String code,
            String label,
            Boolean active,
            Integer severityRank,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new DangerLevel(
                id,
                code,
                label,
                active,
                severityRank,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String label,
            Boolean active,
            Integer severityRank) {
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.active = Objects.requireNonNull(active, "active must not be null");
        this.severityRank = Objects.requireNonNull(severityRank, "severityRank must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new DangerLevelUpdatedEvent(
                this.id,
                this.code,
                this.label,
                this.active,
                this.severityRank,
                now));
    }

    public DangerLevelId id() {
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

    public Integer severityRank() {
        return severityRank;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
