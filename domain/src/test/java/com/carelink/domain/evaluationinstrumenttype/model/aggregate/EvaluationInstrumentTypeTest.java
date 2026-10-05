package com.carelink.domain.evaluationinstrumenttype.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.evaluationinstrumenttype.event.EvaluationInstrumentTypeRegisteredEvent;
import com.carelink.domain.evaluationinstrumenttype.event.EvaluationInstrumentTypeUpdatedEvent;
import org.junit.jupiter.api.Test;

class EvaluationInstrumentTypeTest {
    private static EvaluationInstrumentType sample() {
        return EvaluationInstrumentType.register(
                "Sample",
                "Sample",
                true,
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        EvaluationInstrumentType aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        EvaluationInstrumentTypeRegisteredEvent event = assertInstanceOf(EvaluationInstrumentTypeRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        EvaluationInstrumentType aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                true,
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        EvaluationInstrumentTypeUpdatedEvent event = assertInstanceOf(EvaluationInstrumentTypeUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        EvaluationInstrumentType original = sample();
        EvaluationInstrumentType restored = EvaluationInstrumentType.restore(
                original.id(),
                original.code(),
                original.label(),
                original.active(),
                original.description(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
