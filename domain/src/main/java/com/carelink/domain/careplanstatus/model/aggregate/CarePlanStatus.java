package com.carelink.domain.careplanstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.careplanstatus.event.CarePlanStatusRegisteredEvent;
import com.carelink.domain.careplanstatus.event.CarePlanStatusUpdatedEvent;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.domain.common.model.AggregateRoot;

public class CarePlanStatus extends AggregateRoot {
    private final CarePlanStatusId id;
    private String code;
    private String label;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CarePlanStatus(
            CarePlanStatusId id,
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

    public static CarePlanStatus register(
            String code,
            String label,
            Boolean active) {
        CarePlanStatusId id = CarePlanStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        CarePlanStatus aggregate = new CarePlanStatus(
                id,
                code,
                label,
                active,
                now,
                now);
        aggregate.recordEvent(new CarePlanStatusRegisteredEvent(id, now));
        return aggregate;
    }

    public static CarePlanStatus restore(
            CarePlanStatusId id,
            String code,
            String label,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new CarePlanStatus(
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
        recordEvent(new CarePlanStatusUpdatedEvent(
                this.id,
                this.code,
                this.label,
                this.active,
                now));
    }

    public CarePlanStatusId id() {
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
