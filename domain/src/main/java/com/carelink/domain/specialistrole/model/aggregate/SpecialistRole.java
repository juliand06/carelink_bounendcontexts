package com.carelink.domain.specialistrole.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.specialistrole.event.SpecialistRoleRegisteredEvent;
import com.carelink.domain.specialistrole.event.SpecialistRoleUpdatedEvent;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;

public class SpecialistRole extends AggregateRoot {
    private final SpecialistRoleId id;
    private String title;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private SpecialistRole(
            SpecialistRoleId id,
            String title,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static SpecialistRole register(
            String title) {
        SpecialistRoleId id = SpecialistRoleId.generate();
        LocalDateTime now = LocalDateTime.now();
        SpecialistRole aggregate = new SpecialistRole(
                id,
                title,
                now,
                now);
        aggregate.recordEvent(new SpecialistRoleRegisteredEvent(id, now));
        return aggregate;
    }

    public static SpecialistRole restore(
            SpecialistRoleId id,
            String title,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new SpecialistRole(
                id,
                title,
                createdAt,
                updatedAt);
    }

    public void update(
            String title) {
        this.title = Objects.requireNonNull(title, "title must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new SpecialistRoleUpdatedEvent(
                this.id,
                this.title,
                now));
    }

    public SpecialistRoleId id() {
        return id;
    }

    public String title() {
        return title;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
