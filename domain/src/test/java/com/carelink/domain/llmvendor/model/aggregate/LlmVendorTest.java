package com.carelink.domain.llmvendor.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.llmvendor.event.LlmVendorRegisteredEvent;
import com.carelink.domain.llmvendor.event.LlmVendorUpdatedEvent;
import org.junit.jupiter.api.Test;

class LlmVendorTest {
    private static LlmVendor sample() {
        return LlmVendor.register(
                "Sample",
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterCreatedEvent() {
        LlmVendor aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        LlmVendorRegisteredEvent event = assertInstanceOf(LlmVendorRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        LlmVendor aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                "Sample",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        LlmVendorUpdatedEvent event = assertInstanceOf(LlmVendorUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        LlmVendor original = sample();
        LlmVendor restored = LlmVendor.restore(
                original.id(),
                original.brandName(),
                original.legalName(),
                original.websiteUrl(),
                original.isEnabled(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
