package com.carelink.domain.academicprogram.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.academicprogram.event.AcademicProgramRegisteredEvent;
import com.carelink.domain.academicprogram.event.AcademicProgramUpdatedEvent;
import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.domain.common.model.AggregateRoot;

public class AcademicProgram extends AggregateRoot {
    private final AcademicProgramId id;
    private String title;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AcademicProgram(
            AcademicProgramId id,
            String title,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static AcademicProgram register(
            String title) {
        AcademicProgramId id = AcademicProgramId.generate();
        LocalDateTime now = LocalDateTime.now();
        AcademicProgram aggregate = new AcademicProgram(
                id,
                title,
                now,
                now);
        aggregate.recordEvent(new AcademicProgramRegisteredEvent(id, now));
        return aggregate;
    }

    public static AcademicProgram restore(
            AcademicProgramId id,
            String title,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new AcademicProgram(
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
        recordEvent(new AcademicProgramUpdatedEvent(
                this.id,
                this.title,
                now));
    }

    public AcademicProgramId id() {
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
