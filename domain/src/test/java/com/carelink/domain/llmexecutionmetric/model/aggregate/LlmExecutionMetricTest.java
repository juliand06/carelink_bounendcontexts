package com.carelink.domain.llmexecutionmetric.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionmetric.event.LlmExecutionMetricRegisteredEvent;
import com.carelink.domain.llmexecutionmetric.event.LlmExecutionMetricUpdatedEvent;
import org.junit.jupiter.api.Test;

class LlmExecutionMetricTest {
    private static LlmExecutionMetric sample() {
        return LlmExecutionMetric.register(
                LlmExecutionId.generate(),
                1,
                1,
                1,
                BigDecimal.ONE);
    }

    @Test
    void shouldRegisterCreatedEvent() {
        LlmExecutionMetric aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        LlmExecutionMetricRegisteredEvent event = assertInstanceOf(LlmExecutionMetricRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        LlmExecutionMetric aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                LlmExecutionId.generate(),
                1,
                1,
                1,
                BigDecimal.ONE);
        assertEquals(1, aggregate.domainEvents().size());
        LlmExecutionMetricUpdatedEvent event = assertInstanceOf(LlmExecutionMetricUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        LlmExecutionMetric original = sample();
        LlmExecutionMetric restored = LlmExecutionMetric.restore(
                original.id(),
                original.executionId(),
                original.inputTokens(),
                original.outputTokens(),
                original.totalTokens(),
                original.totalCost(),
                original.createdAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
