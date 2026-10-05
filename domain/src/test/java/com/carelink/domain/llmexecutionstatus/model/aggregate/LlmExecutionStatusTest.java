package com.carelink.domain.llmexecutionstatus.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.llmexecutionstatus.event.LlmExecutionStatusRegisteredEvent;
import com.carelink.domain.llmexecutionstatus.event.LlmExecutionStatusUpdatedEvent;
import org.junit.jupiter.api.Test;

class LlmExecutionStatusTest {
    private static LlmExecutionStatus sample() {
        return LlmExecutionStatus.register(
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        LlmExecutionStatus aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        LlmExecutionStatusRegisteredEvent event = assertInstanceOf(LlmExecutionStatusRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        LlmExecutionStatus aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        LlmExecutionStatusUpdatedEvent event = assertInstanceOf(LlmExecutionStatusUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        LlmExecutionStatus original = sample();
        LlmExecutionStatus restored = LlmExecutionStatus.restore(
                original.id(),
                original.label(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
