package com.carelink.domain.informedconsenttype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.informedconsenttype.event.InformedConsentTypeRegisteredEvent;
import com.carelink.domain.informedconsenttype.event.InformedConsentTypeUpdatedEvent;
import com.carelink.domain.informedconsenttype.model.valueobject.InformedConsentTypeId;

public class InformedConsentType extends AggregateRoot {
    private final InformedConsentTypeId id;
    private String code;
    private String label;
    private Boolean active;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private InformedConsentType(
            InformedConsentTypeId id,
            String code,
            String label,
            Boolean active,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.active = Objects.requireNonNull(active, "active must not be null");
        this.description = description;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static InformedConsentType register(
            String code,
            String label,
            Boolean active,
            String description) {
        InformedConsentTypeId id = InformedConsentTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        InformedConsentType aggregate = new InformedConsentType(
                id,
                code,
                label,
                active,
                description,
                now,
                now);
        aggregate.recordEvent(new InformedConsentTypeRegisteredEvent(id, now));
        return aggregate;
    }

    public static InformedConsentType restore(
            InformedConsentTypeId id,
            String code,
            String label,
            Boolean active,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new InformedConsentType(
                id,
                code,
                label,
                active,
                description,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String label,
            Boolean active,
            String description) {
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.active = Objects.requireNonNull(active, "active must not be null");
        this.description = description;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new InformedConsentTypeUpdatedEvent(
                this.id,
                this.code,
                this.label,
                this.active,
                this.description,
                now));
    }

    public InformedConsentTypeId id() {
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

    public String description() {
        return description;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
