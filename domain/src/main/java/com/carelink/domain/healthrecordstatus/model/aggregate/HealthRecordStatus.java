package com.carelink.domain.healthrecordstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.healthrecordstatus.event.HealthRecordStatusRegisteredEvent;
import com.carelink.domain.healthrecordstatus.event.HealthRecordStatusUpdatedEvent;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;

public class HealthRecordStatus extends AggregateRoot {
    private final HealthRecordStatusId id;
    private String code;
    private String label;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private HealthRecordStatus(
            HealthRecordStatusId id,
            String code,
            String label,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static HealthRecordStatus register(
            String code,
            String label) {
        HealthRecordStatusId id = HealthRecordStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        HealthRecordStatus aggregate = new HealthRecordStatus(
                id,
                code,
                label,
                now,
                now);
        aggregate.recordEvent(new HealthRecordStatusRegisteredEvent(id, now));
        return aggregate;
    }

    public static HealthRecordStatus restore(
            HealthRecordStatusId id,
            String code,
            String label,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new HealthRecordStatus(
                id,
                code,
                label,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String label) {
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new HealthRecordStatusUpdatedEvent(
                this.id,
                this.code,
                this.label,
                now));
    }

    public HealthRecordStatusId id() {
        return id;
    }

    public String code() {
        return code;
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
