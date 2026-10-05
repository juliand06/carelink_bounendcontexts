package com.carelink.domain.appointmentstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointmentstatus.event.AppointmentStatusRegisteredEvent;
import com.carelink.domain.appointmentstatus.event.AppointmentStatusUpdatedEvent;
import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;
import com.carelink.domain.common.model.AggregateRoot;

public class AppointmentStatus extends AggregateRoot {
    private final AppointmentStatusId id;
    private String code;
    private String label;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AppointmentStatus(
            AppointmentStatusId id,
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

    public static AppointmentStatus register(
            String code,
            String label,
            Boolean active) {
        AppointmentStatusId id = AppointmentStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        AppointmentStatus aggregate = new AppointmentStatus(
                id,
                code,
                label,
                active,
                now,
                now);
        aggregate.recordEvent(new AppointmentStatusRegisteredEvent(id, now));
        return aggregate;
    }

    public static AppointmentStatus restore(
            AppointmentStatusId id,
            String code,
            String label,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new AppointmentStatus(
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
        recordEvent(new AppointmentStatusUpdatedEvent(
                this.id,
                this.code,
                this.label,
                this.active,
                now));
    }

    public AppointmentStatusId id() {
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
