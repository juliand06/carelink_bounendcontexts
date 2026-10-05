package com.carelink.domain.specialistrole.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.specialistrole.event.SpecialistRoleRegisteredEvent;
import com.carelink.domain.specialistrole.event.SpecialistRoleUpdatedEvent;
import org.junit.jupiter.api.Test;

class SpecialistRoleTest {
    private static SpecialistRole sample() {
        return SpecialistRole.register(
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        SpecialistRole aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        SpecialistRoleRegisteredEvent event = assertInstanceOf(SpecialistRoleRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        SpecialistRole aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        SpecialistRoleUpdatedEvent event = assertInstanceOf(SpecialistRoleUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        SpecialistRole original = sample();
        SpecialistRole restored = SpecialistRole.restore(
                original.id(),
                original.title(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
