package com.carelink.domain.department.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.department.event.DepartmentRegisteredEvent;
import com.carelink.domain.department.event.DepartmentUpdatedEvent;
import com.carelink.domain.department.model.valueobject.DepartmentId;

public class Department extends AggregateRoot {
    private final DepartmentId id;
    private String name;
    private String isoCode;
    private String notes;
    private Boolean enabled;
    private CountryId countryId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Department(
            DepartmentId id,
            String name,
            String isoCode,
            String notes,
            Boolean enabled,
            CountryId countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.isoCode = Objects.requireNonNull(isoCode, "isoCode must not be null");
        this.notes = notes;
        this.enabled = Objects.requireNonNull(enabled, "enabled must not be null");
        this.countryId = Objects.requireNonNull(countryId, "countryId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static Department register(
            String name,
            String isoCode,
            String notes,
            Boolean enabled,
            CountryId countryId) {
        DepartmentId id = DepartmentId.generate();
        LocalDateTime now = LocalDateTime.now();
        Department aggregate = new Department(
                id,
                name,
                isoCode,
                notes,
                enabled,
                countryId,
                now,
                now);
        aggregate.recordEvent(new DepartmentRegisteredEvent(id, now));
        return aggregate;
    }

    public static Department restore(
            DepartmentId id,
            String name,
            String isoCode,
            String notes,
            Boolean enabled,
            CountryId countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Department(
                id,
                name,
                isoCode,
                notes,
                enabled,
                countryId,
                createdAt,
                updatedAt);
    }

    public void update(
            String name,
            String isoCode,
            String notes,
            Boolean enabled,
            CountryId countryId) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.isoCode = Objects.requireNonNull(isoCode, "isoCode must not be null");
        this.notes = notes;
        this.enabled = Objects.requireNonNull(enabled, "enabled must not be null");
        this.countryId = Objects.requireNonNull(countryId, "countryId must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new DepartmentUpdatedEvent(
                this.id,
                this.name,
                this.isoCode,
                this.notes,
                this.enabled,
                this.countryId,
                now));
    }

    public DepartmentId id() {
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

    public CountryId countryId() {
        return countryId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
