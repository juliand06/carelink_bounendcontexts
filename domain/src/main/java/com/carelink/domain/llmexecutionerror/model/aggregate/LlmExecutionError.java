package com.carelink.domain.llmexecutionerror.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionerror.event.LlmExecutionErrorRegisteredEvent;
import com.carelink.domain.llmexecutionerror.event.LlmExecutionErrorUpdatedEvent;
import com.carelink.domain.llmexecutionerror.model.valueobject.LlmExecutionErrorId;

public class LlmExecutionError extends AggregateRoot {
    private final LlmExecutionErrorId id;
    private LlmExecutionId executionId;
    private String errorDetail;
    private String errorCode;
    private String vendorErrorRef;
    private final LocalDateTime createdAt;

    private LlmExecutionError(
            LlmExecutionErrorId id,
            LlmExecutionId executionId,
            String errorDetail,
            String errorCode,
            String vendorErrorRef,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.executionId = Objects.requireNonNull(executionId, "executionId must not be null");
        this.errorDetail = Objects.requireNonNull(errorDetail, "errorDetail must not be null");
        this.errorCode = errorCode;
        this.vendorErrorRef = vendorErrorRef;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
    }

    public static LlmExecutionError register(
            LlmExecutionId executionId,
            String errorDetail,
            String errorCode,
            String vendorErrorRef) {
        LlmExecutionErrorId id = LlmExecutionErrorId.generate();
        LocalDateTime now = LocalDateTime.now();
        LlmExecutionError aggregate = new LlmExecutionError(
                id,
                executionId,
                errorDetail,
                errorCode,
                vendorErrorRef,
                now);
        aggregate.recordEvent(new LlmExecutionErrorRegisteredEvent(id, now));
        return aggregate;
    }

    public static LlmExecutionError restore(
            LlmExecutionErrorId id,
            LlmExecutionId executionId,
            String errorDetail,
            String errorCode,
            String vendorErrorRef,
            LocalDateTime createdAt) {
        return new LlmExecutionError(
                id,
                executionId,
                errorDetail,
                errorCode,
                vendorErrorRef,
                createdAt);
    }

    public void update(
            LlmExecutionId executionId,
            String errorDetail,
            String errorCode,
            String vendorErrorRef) {
        this.executionId = Objects.requireNonNull(executionId, "executionId must not be null");
        this.errorDetail = Objects.requireNonNull(errorDetail, "errorDetail must not be null");
        this.errorCode = errorCode;
        this.vendorErrorRef = vendorErrorRef;
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new LlmExecutionErrorUpdatedEvent(
                this.id,
                this.executionId,
                this.errorDetail,
                this.errorCode,
                this.vendorErrorRef,
                now));
    }

    public LlmExecutionErrorId id() {
        return id;
    }

    public LlmExecutionId executionId() {
        return executionId;
    }

    public String errorDetail() {
        return errorDetail;
    }

    public String errorCode() {
        return errorCode;
    }

    public String vendorErrorRef() {
        return vendorErrorRef;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
