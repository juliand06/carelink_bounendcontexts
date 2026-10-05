package com.carelink.domain.llmexecutionstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.llmexecutionstatus.event.LlmExecutionStatusRegisteredEvent;
import com.carelink.domain.llmexecutionstatus.event.LlmExecutionStatusUpdatedEvent;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;

public class LlmExecutionStatus extends AggregateRoot {
    private final LlmExecutionStatusId id;
    private String label;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private LlmExecutionStatus(
            LlmExecutionStatusId id,
            String label,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static LlmExecutionStatus register(
            String label) {
        LlmExecutionStatusId id = LlmExecutionStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        LlmExecutionStatus aggregate = new LlmExecutionStatus(
                id,
                label,
                now,
                now);
        aggregate.recordEvent(new LlmExecutionStatusRegisteredEvent(id, now));
        return aggregate;
    }

    public static LlmExecutionStatus restore(
            LlmExecutionStatusId id,
            String label,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new LlmExecutionStatus(
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
        recordEvent(new LlmExecutionStatusUpdatedEvent(
                this.id,
                this.label,
                now));
    }

    public LlmExecutionStatusId id() {
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
