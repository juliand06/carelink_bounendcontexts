package com.carelink.domain.iddocumenttype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.iddocumenttype.event.IdDocumentTypeRegisteredEvent;
import com.carelink.domain.iddocumenttype.event.IdDocumentTypeUpdatedEvent;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;

public class IdDocumentType extends AggregateRoot {
    private final IdDocumentTypeId id;
    private String code;
    private String label;
    private Boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private IdDocumentType(
            IdDocumentTypeId id,
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

    public static IdDocumentType register(
            String code,
            String label,
            Boolean active) {
        IdDocumentTypeId id = IdDocumentTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        IdDocumentType aggregate = new IdDocumentType(
                id,
                code,
                label,
                active,
                now,
                now);
        aggregate.recordEvent(new IdDocumentTypeRegisteredEvent(id, now));
        return aggregate;
    }

    public static IdDocumentType restore(
            IdDocumentTypeId id,
            String code,
            String label,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new IdDocumentType(
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
        recordEvent(new IdDocumentTypeUpdatedEvent(
                this.id,
                this.code,
                this.label,
                this.active,
                now));
    }

    public IdDocumentTypeId id() {
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
