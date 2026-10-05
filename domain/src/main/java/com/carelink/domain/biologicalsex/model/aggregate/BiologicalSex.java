package com.carelink.domain.biologicalsex.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.biologicalsex.event.BiologicalSexRegisteredEvent;
import com.carelink.domain.biologicalsex.event.BiologicalSexUpdatedEvent;
import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;
import com.carelink.domain.common.model.AggregateRoot;

public class BiologicalSex extends AggregateRoot {
    private final BiologicalSexId id;
    private String label;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private BiologicalSex(
            BiologicalSexId id,
            String label,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static BiologicalSex register(
            String label) {
        BiologicalSexId id = BiologicalSexId.generate();
        LocalDateTime now = LocalDateTime.now();
        BiologicalSex aggregate = new BiologicalSex(
                id,
                label,
                now,
                now);
        aggregate.recordEvent(new BiologicalSexRegisteredEvent(id, now));
        return aggregate;
    }

    public static BiologicalSex restore(
            BiologicalSexId id,
            String label,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new BiologicalSex(
                id,
                label,
                createdAt,
                updatedAt);
    }

    public void update(
            String label) {
        this.label = Objects.requireNonNull(label, "label must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new BiologicalSexUpdatedEvent(
                this.id,
                this.label,
                now));
    }

    public BiologicalSexId id() {
        return id;
    }

    public String label() {
        return label;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
