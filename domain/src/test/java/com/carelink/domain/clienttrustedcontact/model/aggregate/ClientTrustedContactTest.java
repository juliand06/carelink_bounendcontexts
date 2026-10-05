package com.carelink.domain.clienttrustedcontact.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.clienttrustedcontact.event.ClientTrustedContactRegisteredEvent;
import com.carelink.domain.clienttrustedcontact.event.ClientTrustedContactUpdatedEvent;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import org.junit.jupiter.api.Test;

class ClientTrustedContactTest {
    private static ClientTrustedContact sample() {
        return ClientTrustedContact.register(
                ClientId.generate(),
                TrustedContactId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        ClientTrustedContact aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        ClientTrustedContactRegisteredEvent event = assertInstanceOf(ClientTrustedContactRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        ClientTrustedContact aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                ClientId.generate(),
                TrustedContactId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        ClientTrustedContactUpdatedEvent event = assertInstanceOf(ClientTrustedContactUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        ClientTrustedContact original = sample();
        ClientTrustedContact restored = ClientTrustedContact.restore(
                original.id(),
                original.clientId(),
                original.trustedContactId(),
                original.createdAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
