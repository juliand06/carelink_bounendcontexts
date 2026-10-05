package com.carelink.domain.drugadministrationroute.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.drugadministrationroute.event.DrugAdministrationRouteRegisteredEvent;
import com.carelink.domain.drugadministrationroute.event.DrugAdministrationRouteUpdatedEvent;
import com.carelink.domain.drugadministrationroute.model.valueobject.DrugAdministrationRouteId;

public class DrugAdministrationRoute extends AggregateRoot {
    private final DrugAdministrationRouteId id;
    private String code;
    private String label;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private DrugAdministrationRoute(
            DrugAdministrationRouteId id,
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

    public static DrugAdministrationRoute register(
            String code,
            String label,
            Boolean active) {
        DrugAdministrationRouteId id = DrugAdministrationRouteId.generate();
        LocalDateTime now = LocalDateTime.now();
        DrugAdministrationRoute aggregate = new DrugAdministrationRoute(
                id,
                code,
                label,
                active,
                now,
                now);
        aggregate.recordEvent(new DrugAdministrationRouteRegisteredEvent(id, now));
        return aggregate;
    }

    public static DrugAdministrationRoute restore(
            DrugAdministrationRouteId id,
            String code,
            String label,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new DrugAdministrationRoute(
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
        recordEvent(new DrugAdministrationRouteUpdatedEvent(
                this.id,
                this.code,
                this.label,
                this.active,
                now));
    }

    public DrugAdministrationRouteId id() {
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
