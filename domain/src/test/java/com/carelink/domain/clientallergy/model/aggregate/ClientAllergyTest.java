package com.carelink.domain.clientallergy.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.clientallergy.event.ClientAllergyRegisteredEvent;
import com.carelink.domain.clientallergy.event.ClientAllergyUpdatedEvent;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class ClientAllergyTest {
    private static ClientAllergy sample() {
        return ClientAllergy.register(
                ClientId.generate(),
                "Sample",
                "Sample",
                "Sample",
                true,
                SpecialistId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        ClientAllergy aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        ClientAllergyRegisteredEvent event = assertInstanceOf(ClientAllergyRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        ClientAllergy aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                ClientId.generate(),
                "Sample",
                "Sample",
                "Sample",
                true,
                SpecialistId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        ClientAllergyUpdatedEvent event = assertInstanceOf(ClientAllergyUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        ClientAllergy original = sample();
        ClientAllergy restored = ClientAllergy.restore(
                original.id(),
                original.clientId(),
                original.allergen(),
                original.reactionDescription(),
                original.severityLevel(),
                original.active(),
                original.registeredAt(),
                original.registeredBy(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
