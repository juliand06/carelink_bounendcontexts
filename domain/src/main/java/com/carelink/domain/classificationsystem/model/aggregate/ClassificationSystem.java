package com.carelink.domain.classificationsystem.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.classificationsystem.event.ClassificationSystemRegisteredEvent;
import com.carelink.domain.classificationsystem.event.ClassificationSystemUpdatedEvent;
import com.carelink.domain.classificationsystem.model.valueobject.ClassificationSystemId;
import com.carelink.domain.common.model.AggregateRoot;

public class ClassificationSystem extends AggregateRoot {
    private final ClassificationSystemId id;
    private String code;
    private String label;
    private Boolean active;
    private String edition;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ClassificationSystem(
            ClassificationSystemId id,
            String code,
            String label,
            Boolean active,
            String edition,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.active = Objects.requireNonNull(active, "active must not be null");
        this.edition = edition;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ClassificationSystem register(
            String code,
            String label,
            Boolean active,
            String edition) {
        ClassificationSystemId id = ClassificationSystemId.generate();
        LocalDateTime now = LocalDateTime.now();
        ClassificationSystem aggregate = new ClassificationSystem(
                id,
                code,
                label,
                active,
                edition,
                now,
                now);
        aggregate.recordEvent(new ClassificationSystemRegisteredEvent(id, now));
        return aggregate;
    }

    public static ClassificationSystem restore(
            ClassificationSystemId id,
            String code,
            String label,
            Boolean active,
            String edition,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ClassificationSystem(
                id,
                code,
                label,
                active,
                edition,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String label,
            Boolean active,
            String edition) {
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.active = Objects.requireNonNull(active, "active must not be null");
        this.edition = edition;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ClassificationSystemUpdatedEvent(
                this.id,
                this.code,
                this.label,
                this.active,
                this.edition,
                now));
    }

    public ClassificationSystemId id() {
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

    public String edition() {
        return edition;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
