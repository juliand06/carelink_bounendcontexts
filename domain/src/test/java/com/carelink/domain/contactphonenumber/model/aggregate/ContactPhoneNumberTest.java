package com.carelink.domain.contactphonenumber.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.contactphonenumber.event.ContactPhoneNumberRegisteredEvent;
import com.carelink.domain.contactphonenumber.event.ContactPhoneNumberUpdatedEvent;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import org.junit.jupiter.api.Test;

class ContactPhoneNumberTest {
    private static ContactPhoneNumber sample() {
        return ContactPhoneNumber.register(
                TrustedContactId.generate(),
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        ContactPhoneNumber aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        ContactPhoneNumberRegisteredEvent event = assertInstanceOf(ContactPhoneNumberRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        ContactPhoneNumber aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                TrustedContactId.generate(),
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        ContactPhoneNumberUpdatedEvent event = assertInstanceOf(ContactPhoneNumberUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        ContactPhoneNumber original = sample();
        ContactPhoneNumber restored = ContactPhoneNumber.restore(
                original.id(),
                original.trustedContactId(),
                original.phoneNumber(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
