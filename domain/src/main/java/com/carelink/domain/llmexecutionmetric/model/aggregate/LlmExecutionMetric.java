package com.carelink.domain.llmexecutionmetric.model.aggregate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionmetric.event.LlmExecutionMetricRegisteredEvent;
import com.carelink.domain.llmexecutionmetric.event.LlmExecutionMetricUpdatedEvent;
import com.carelink.domain.llmexecutionmetric.model.valueobject.LlmExecutionMetricId;

public class LlmExecutionMetric extends AggregateRoot {
    private final LlmExecutionMetricId id;
    private LlmExecutionId executionId;
    private Integer inputTokens;
    private Integer outputTokens;
    private Integer totalTokens;
    private BigDecimal totalCost;
    private final LocalDateTime createdAt;

    private LlmExecutionMetric(
            LlmExecutionMetricId id,
            LlmExecutionId executionId,
            Integer inputTokens,
            Integer outputTokens,
            Integer totalTokens,
            BigDecimal totalCost,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.executionId = Objects.requireNonNull(executionId, "executionId must not be null");
        this.inputTokens = Objects.requireNonNull(inputTokens, "inputTokens must not be null");
        this.outputTokens = Objects.requireNonNull(outputTokens, "outputTokens must not be null");
        this.totalTokens = Objects.requireNonNull(totalTokens, "totalTokens must not be null");
        this.totalCost = Objects.requireNonNull(totalCost, "totalCost must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
    }

    public static LlmExecutionMetric register(
            LlmExecutionId executionId,
            Integer inputTokens,
            Integer outputTokens,
            Integer totalTokens,
            BigDecimal totalCost) {
        LlmExecutionMetricId id = LlmExecutionMetricId.generate();
        LocalDateTime now = LocalDateTime.now();
        LlmExecutionMetric aggregate = new LlmExecutionMetric(
                id,
                executionId,
                inputTokens,
                outputTokens,
                totalTokens,
                totalCost,
                now);
        aggregate.recordEvent(new LlmExecutionMetricRegisteredEvent(id, now));
        return aggregate;
    }

    public static LlmExecutionMetric restore(
            LlmExecutionMetricId id,
            LlmExecutionId executionId,
            Integer inputTokens,
            Integer outputTokens,
            Integer totalTokens,
            BigDecimal totalCost,
            LocalDateTime createdAt) {
        return new LlmExecutionMetric(
                id,
                executionId,
                inputTokens,
                outputTokens,
                totalTokens,
                totalCost,
                createdAt);
    }

    public void update(
            LlmExecutionId executionId,
            Integer inputTokens,
            Integer outputTokens,
            Integer totalTokens,
            BigDecimal totalCost) {
        this.executionId = Objects.requireNonNull(executionId, "executionId must not be null");
        this.inputTokens = Objects.requireNonNull(inputTokens, "inputTokens must not be null");
        this.outputTokens = Objects.requireNonNull(outputTokens, "outputTokens must not be null");
        this.totalTokens = Objects.requireNonNull(totalTokens, "totalTokens must not be null");
        this.totalCost = Objects.requireNonNull(totalCost, "totalCost must not be null");
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new LlmExecutionMetricUpdatedEvent(
                this.id,
                this.executionId,
                this.inputTokens,
                this.outputTokens,
                this.totalTokens,
                this.totalCost,
                now));
    }

    public LlmExecutionMetricId id() {
        return id;
    }

    public LlmExecutionId executionId() {
        return executionId;
    }

    public Integer inputTokens() {
        return inputTokens;
    }

    public Integer outputTokens() {
        return outputTokens;
    }

    public Integer totalTokens() {
        return totalTokens;
    }

    public BigDecimal totalCost() {
        return totalCost;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
