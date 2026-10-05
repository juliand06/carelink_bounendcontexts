package com.carelink.domain.healthrecord.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.healthrecord.event.HealthRecordRegisteredEvent;
import com.carelink.domain.healthrecord.event.HealthRecordUpdatedEvent;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class HealthRecordTest {
    private static HealthRecord sample() {
        return HealthRecord.register(
                ClientId.generate(),
                "Sample",
                LocalDateTime.of(2026, 1, 10, 8, 0),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                HealthRecordStatusId.generate(),
                SpecialistId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        HealthRecord aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        HealthRecordRegisteredEvent event = assertInstanceOf(HealthRecordRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        HealthRecord aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                ClientId.generate(),
                "Sample",
                LocalDateTime.of(2026, 1, 10, 8, 0),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                HealthRecordStatusId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        HealthRecordUpdatedEvent event = assertInstanceOf(HealthRecordUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        HealthRecord original = sample();
        HealthRecord restored = HealthRecord.restore(
                original.id(),
                original.clientId(),
                original.openingDate(),
                original.recordCode(),
                original.openedAt(),
                original.closedAt(),
                original.statusId(),
                original.createdAt(),
                original.createdBy());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
