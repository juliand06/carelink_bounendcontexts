package com.carelink.domain.client.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;
import com.carelink.domain.client.event.ClientRegisteredEvent;
import com.carelink.domain.client.event.ClientUpdatedEvent;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class ClientTest {
    private static Client sample() {
        return Client.register(
                IdDocumentTypeId.generate(),
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                LocalDate.of(2000, 1, 1),
                BiologicalSexId.generate(),
                BiologicalSexId.generate(),
                "Sample",
                "Sample",
                "Sample",
                true,
                SpecialistId.generate(),
                SpecialistId.generate(),
                MunicipalityId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        Client aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        ClientRegisteredEvent event = assertInstanceOf(ClientRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        Client aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                IdDocumentTypeId.generate(),
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                LocalDate.of(2000, 1, 1),
                BiologicalSexId.generate(),
                BiologicalSexId.generate(),
                "Sample",
                "Sample",
                "Sample",
                true,
                SpecialistId.generate(),
                MunicipalityId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        ClientUpdatedEvent event = assertInstanceOf(ClientUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        Client original = sample();
        Client restored = Client.restore(
                original.id(),
                original.idDocumentTypeId(),
                original.documentNumber(),
                original.firstName(),
                original.middleName(),
                original.lastName(),
                original.secondLastName(),
                original.birthDate(),
                original.biologicalSexId(),
                original.genderIdentityId(),
                original.email(),
                original.mobilePhone(),
                original.homeAddress(),
                original.active(),
                original.createdAt(),
                original.createdBy(),
                original.updatedAt(),
                original.updatedBy(),
                original.municipalityId());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
