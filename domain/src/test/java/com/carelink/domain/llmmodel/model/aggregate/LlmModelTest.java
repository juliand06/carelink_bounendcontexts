package com.carelink.domain.llmmodel.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.carelink.domain.llmmodel.event.LlmModelRegisteredEvent;
import com.carelink.domain.llmmodel.event.LlmModelUpdatedEvent;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;
import org.junit.jupiter.api.Test;

class LlmModelTest {
    private static LlmModel sample() {
        return LlmModel.register(
                LlmVendorId.generate(),
                "Sample",
                "Sample",
                BigDecimal.ONE,
                BigDecimal.ONE,
                1,
                1,
                true);
    }

    @Test
    void shouldRegisterCreatedEvent() {
        LlmModel aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        LlmModelRegisteredEvent event = assertInstanceOf(LlmModelRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        LlmModel aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                LlmVendorId.generate(),
                "Sample",
                "Sample",
                BigDecimal.ONE,
                BigDecimal.ONE,
                1,
                1,
                true);
        assertEquals(1, aggregate.domainEvents().size());
        LlmModelUpdatedEvent event = assertInstanceOf(LlmModelUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        LlmModel original = sample();
        LlmModel restored = LlmModel.restore(
                original.id(),
                original.llmVendorId(),
                original.displayName(),
                original.apiIdentifier(),
                original.pricePerInputToken(),
                original.pricePerOutputToken(),
                original.maxOutputTokens(),
                original.contextLength(),
                original.isEnabled(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
