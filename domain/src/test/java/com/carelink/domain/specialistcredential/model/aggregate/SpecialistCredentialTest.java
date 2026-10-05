package com.carelink.domain.specialistcredential.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialistcredential.event.SpecialistCredentialRegisteredEvent;
import com.carelink.domain.specialistcredential.event.SpecialistCredentialUpdatedEvent;
import org.junit.jupiter.api.Test;

class SpecialistCredentialTest {
    private static SpecialistCredential sample() {
        return SpecialistCredential.register(
                AcademicProgramId.generate(),
                SpecialistId.generate(),
                "Sample",
                "Sample",
                true,
                "Sample",
                CountryId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        SpecialistCredential aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        SpecialistCredentialRegisteredEvent event = assertInstanceOf(SpecialistCredentialRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        SpecialistCredential aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                AcademicProgramId.generate(),
                SpecialistId.generate(),
                "Sample",
                "Sample",
                true,
                "Sample",
                CountryId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        SpecialistCredentialUpdatedEvent event = assertInstanceOf(SpecialistCredentialUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        SpecialistCredential original = sample();
        SpecialistCredential restored = SpecialistCredential.restore(
                original.id(),
                original.academicProgramId(),
                original.specialistId(),
                original.degreeTitle(),
                original.institution(),
                original.isVerified(),
                original.resolutionCode(),
                original.countryId(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
