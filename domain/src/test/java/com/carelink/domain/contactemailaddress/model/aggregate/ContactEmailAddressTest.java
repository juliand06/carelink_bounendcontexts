package com.carelink.domain.contactemailaddress.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.contactemailaddress.event.ContactEmailAddressRegisteredEvent;
import com.carelink.domain.contactemailaddress.event.ContactEmailAddressUpdatedEvent;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import org.junit.jupiter.api.Test;

class ContactEmailAddressTest {
    private static ContactEmailAddress sample() {
        return ContactEmailAddress.register(
                TrustedContactId.generate(),
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        ContactEmailAddress aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        ContactEmailAddressRegisteredEvent event = assertInstanceOf(ContactEmailAddressRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        ContactEmailAddress aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                TrustedContactId.generate(),
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        ContactEmailAddressUpdatedEvent event = assertInstanceOf(ContactEmailAddressUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        ContactEmailAddress original = sample();
        ContactEmailAddress restored = ContactEmailAddress.restore(
                original.id(),
                original.trustedContactId(),
                original.emailAddress(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
