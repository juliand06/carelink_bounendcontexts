package com.carelink.domain.specialist.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.specialist.event.SpecialistRegisteredEvent;
import com.carelink.domain.specialist.event.SpecialistUpdatedEvent;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;
import org.junit.jupiter.api.Test;

class SpecialistTest {
    private static Specialist sample() {
        return Specialist.register(
                IdDocumentTypeId.generate(),
                "Sample",
                "Sample",
                "Sample",
                SpecialistRoleId.generate(),
                "Sample",
                true,
                MunicipalityId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        Specialist aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        SpecialistRegisteredEvent event = assertInstanceOf(SpecialistRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        Specialist aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                IdDocumentTypeId.generate(),
                "Sample",
                "Sample",
                "Sample",
                SpecialistRoleId.generate(),
                "Sample",
                true,
                MunicipalityId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        SpecialistUpdatedEvent event = assertInstanceOf(SpecialistUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        Specialist original = sample();
        Specialist restored = Specialist.restore(
                original.id(),
                original.idDocumentTypeId(),
                original.documentNumber(),
                original.firstName(),
                original.lastName(),
                original.specialistRoleId(),
                original.professionalLicense(),
                original.active(),
                original.municipalityId(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
