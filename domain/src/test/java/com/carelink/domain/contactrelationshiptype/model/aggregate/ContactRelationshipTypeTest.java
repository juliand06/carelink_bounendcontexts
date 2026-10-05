package com.carelink.domain.contactrelationshiptype.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.contactrelationshiptype.event.ContactRelationshipTypeRegisteredEvent;
import com.carelink.domain.contactrelationshiptype.event.ContactRelationshipTypeUpdatedEvent;
import org.junit.jupiter.api.Test;

class ContactRelationshipTypeTest {
    private static ContactRelationshipType sample() {
        return ContactRelationshipType.register(
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        ContactRelationshipType aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        ContactRelationshipTypeRegisteredEvent event = assertInstanceOf(ContactRelationshipTypeRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        ContactRelationshipType aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        ContactRelationshipTypeUpdatedEvent event = assertInstanceOf(ContactRelationshipTypeUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        ContactRelationshipType original = sample();
        ContactRelationshipType restored = ContactRelationshipType.restore(
                original.id(),
                original.label());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
