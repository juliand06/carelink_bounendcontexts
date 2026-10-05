package com.carelink.domain.municipality.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.department.model.valueobject.DepartmentId;
import com.carelink.domain.municipality.event.MunicipalityRegisteredEvent;
import com.carelink.domain.municipality.event.MunicipalityUpdatedEvent;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;

public class Municipality extends AggregateRoot {
    private final MunicipalityId id;
    private String name;
    private String isoCode;
    private String notes;
    private Boolean enabled;
    private DepartmentId departmentId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Municipality(
            MunicipalityId id,
            String name,
            String isoCode,
            String notes,
            Boolean enabled,
            DepartmentId departmentId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.isoCode = Objects.requireNonNull(isoCode, "isoCode must not be null");
        this.notes = notes;
        this.enabled = Objects.requireNonNull(enabled, "enabled must not be null");
        this.departmentId = Objects.requireNonNull(departmentId, "departmentId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static Municipality register(
            String name,
            String isoCode,
            String notes,
            Boolean enabled,
            DepartmentId departmentId) {
        MunicipalityId id = MunicipalityId.generate();
        LocalDateTime now = LocalDateTime.now();
        Municipality aggregate = new Municipality(
                id,
                name,
                isoCode,
                notes,
                enabled,
                departmentId,
                now,
                now);
        aggregate.recordEvent(new MunicipalityRegisteredEvent(id, now));
        return aggregate;
    }

    public static Municipality restore(
            MunicipalityId id,
            String name,
            String isoCode,
            String notes,
            Boolean enabled,
            DepartmentId departmentId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Municipality(
                id,
                name,
                isoCode,
                notes,
                enabled,
                departmentId,
                createdAt,
                updatedAt);
    }

    public void update(
            String name,
            String isoCode,
            String notes,
            Boolean enabled,
            DepartmentId departmentId) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.isoCode = Objects.requireNonNull(isoCode, "isoCode must not be null");
        this.notes = notes;
        this.enabled = Objects.requireNonNull(enabled, "enabled must not be null");
        this.departmentId = Objects.requireNonNull(departmentId, "departmentId must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new MunicipalityUpdatedEvent(
                this.id,
                this.name,
                this.isoCode,
                this.notes,
                this.enabled,
                this.departmentId,
                now));
    }

    public MunicipalityId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String isoCode() {
        return isoCode;
    }

    public String notes() {
        return notes;
    }

    public Boolean enabled() {
        return enabled;
    }

    public DepartmentId departmentId() {
        return departmentId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
