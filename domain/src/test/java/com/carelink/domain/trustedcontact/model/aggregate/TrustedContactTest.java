package com.carelink.domain.trustedcontact.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;
import com.carelink.domain.trustedcontact.event.TrustedContactRegisteredEvent;
import com.carelink.domain.trustedcontact.event.TrustedContactUpdatedEvent;
import org.junit.jupiter.api.Test;

class TrustedContactTest {
    private static TrustedContact sample() {
        return TrustedContact.register(
                "Sample",
                ContactRelationshipTypeId.generate(),
                true,
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        TrustedContact aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        TrustedContactRegisteredEvent event = assertInstanceOf(TrustedContactRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        TrustedContact aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                ContactRelationshipTypeId.generate(),
                true,
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        TrustedContactUpdatedEvent event = assertInstanceOf(TrustedContactUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        TrustedContact original = sample();
        TrustedContact restored = TrustedContact.restore(
                original.id(),
                original.fullName(),
                original.relationshipTypeId(),
                original.isPrimaryContact(),
                original.notes(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
