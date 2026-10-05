package com.carelink.domain.country.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.country.event.CountryRegisteredEvent;
import com.carelink.domain.country.event.CountryUpdatedEvent;
import com.carelink.domain.country.model.valueobject.CountryId;

public class Country extends AggregateRoot {
    private final CountryId id;
    private String name;
    private String isoCode;
    private String notes;
    private Boolean enabled;
    private String dialPrefix;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Country(
            CountryId id,
            String name,
            String isoCode,
            String notes,
            Boolean enabled,
            String dialPrefix,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.isoCode = Objects.requireNonNull(isoCode, "isoCode must not be null");
        this.notes = notes;
        this.enabled = Objects.requireNonNull(enabled, "enabled must not be null");
        this.dialPrefix = dialPrefix;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static Country register(
            String name,
            String isoCode,
            String notes,
            Boolean enabled,
            String dialPrefix) {
        CountryId id = CountryId.generate();
        LocalDateTime now = LocalDateTime.now();
        Country aggregate = new Country(
                id,
                name,
                isoCode,
                notes,
                enabled,
                dialPrefix,
                now,
                now);
        aggregate.recordEvent(new CountryRegisteredEvent(id, now));
        return aggregate;
    }

    public static Country restore(
            CountryId id,
            String name,
            String isoCode,
            String notes,
            Boolean enabled,
            String dialPrefix,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Country(
                id,
                name,
                isoCode,
                notes,
                enabled,
                dialPrefix,
                createdAt,
                updatedAt);
    }

    public void update(
            String name,
            String isoCode,
            String notes,
            Boolean enabled,
            String dialPrefix) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.isoCode = Objects.requireNonNull(isoCode, "isoCode must not be null");
        this.notes = notes;
        this.enabled = Objects.requireNonNull(enabled, "enabled must not be null");
        this.dialPrefix = dialPrefix;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new CountryUpdatedEvent(
                this.id,
                this.name,
                this.isoCode,
                this.notes,
                this.enabled,
                this.dialPrefix,
                now));
    }

    public CountryId id() {
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

    public String dialPrefix() {
        return dialPrefix;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
