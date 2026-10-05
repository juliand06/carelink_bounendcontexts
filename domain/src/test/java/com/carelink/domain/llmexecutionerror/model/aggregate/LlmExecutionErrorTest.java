package com.carelink.domain.llmexecutionerror.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionerror.event.LlmExecutionErrorRegisteredEvent;
import com.carelink.domain.llmexecutionerror.event.LlmExecutionErrorUpdatedEvent;
import org.junit.jupiter.api.Test;

class LlmExecutionErrorTest {
    private static LlmExecutionError sample() {
        return LlmExecutionError.register(
                LlmExecutionId.generate(),
                "Sample",
                "Sample",
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        LlmExecutionError aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        LlmExecutionErrorRegisteredEvent event = assertInstanceOf(LlmExecutionErrorRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        LlmExecutionError aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                LlmExecutionId.generate(),
                "Sample",
                "Sample",
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        LlmExecutionErrorUpdatedEvent event = assertInstanceOf(LlmExecutionErrorUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        LlmExecutionError original = sample();
        LlmExecutionError restored = LlmExecutionError.restore(
                original.id(),
                original.executionId(),
                original.errorDetail(),
                original.errorCode(),
                original.vendorErrorRef(),
                original.createdAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
