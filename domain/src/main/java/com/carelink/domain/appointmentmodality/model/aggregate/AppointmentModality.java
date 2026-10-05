package com.carelink.domain.appointmentmodality.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointmentmodality.event.AppointmentModalityRegisteredEvent;
import com.carelink.domain.appointmentmodality.event.AppointmentModalityUpdatedEvent;
import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;
import com.carelink.domain.common.model.AggregateRoot;

public class AppointmentModality extends AggregateRoot {
    private final AppointmentModalityId id;
    private String code;
    private String label;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AppointmentModality(
            AppointmentModalityId id,
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

    public static AppointmentModality register(
            String code,
            String label,
            Boolean active) {
        AppointmentModalityId id = AppointmentModalityId.generate();
        LocalDateTime now = LocalDateTime.now();
        AppointmentModality aggregate = new AppointmentModality(
                id,
                code,
                label,
                active,
                now,
                now);
        aggregate.recordEvent(new AppointmentModalityRegisteredEvent(id, now));
        return aggregate;
    }

    public static AppointmentModality restore(
            AppointmentModalityId id,
            String code,
            String label,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new AppointmentModality(
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
        recordEvent(new AppointmentModalityUpdatedEvent(
                this.id,
                this.code,
                this.label,
                this.active,
                now));
    }

    public AppointmentModalityId id() {
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
