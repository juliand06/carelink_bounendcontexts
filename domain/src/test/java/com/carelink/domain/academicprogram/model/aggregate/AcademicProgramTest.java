package com.carelink.domain.academicprogram.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.academicprogram.event.AcademicProgramRegisteredEvent;
import com.carelink.domain.academicprogram.event.AcademicProgramUpdatedEvent;
import org.junit.jupiter.api.Test;

class AcademicProgramTest {
    private static AcademicProgram sample() {
        return AcademicProgram.register(
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        AcademicProgram aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        AcademicProgramRegisteredEvent event = assertInstanceOf(AcademicProgramRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        AcademicProgram aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        AcademicProgramUpdatedEvent event = assertInstanceOf(AcademicProgramUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        AcademicProgram original = sample();
        AcademicProgram restored = AcademicProgram.restore(
                original.id(),
                original.title(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
