package com.carelink.domain.appointmenttype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointmenttype.event.AppointmentTypeRegisteredEvent;
import com.carelink.domain.appointmenttype.event.AppointmentTypeUpdatedEvent;
import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;
import com.carelink.domain.common.model.AggregateRoot;

public class AppointmentType extends AggregateRoot {
    private final AppointmentTypeId id;
    private String code;
    private String label;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AppointmentType(
            AppointmentTypeId id,
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

    public static AppointmentType register(
            String code,
            String label,
            Boolean active) {
        AppointmentTypeId id = AppointmentTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        AppointmentType aggregate = new AppointmentType(
                id,
                code,
                label,
                active,
                now,
                now);
        aggregate.recordEvent(new AppointmentTypeRegisteredEvent(id, now));
        return aggregate;
    }

    public static AppointmentType restore(
            AppointmentTypeId id,
            String code,
            String label,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new AppointmentType(
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
        recordEvent(new AppointmentTypeUpdatedEvent(
                this.id,
                this.code,
                this.label,
                this.active,
                now));
    }

    public AppointmentTypeId id() {
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
