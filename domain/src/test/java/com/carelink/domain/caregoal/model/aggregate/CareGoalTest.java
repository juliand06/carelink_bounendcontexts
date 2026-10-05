package com.carelink.domain.caregoal.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.carelink.domain.caregoal.event.CareGoalRegisteredEvent;
import com.carelink.domain.caregoal.event.CareGoalUpdatedEvent;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import org.junit.jupiter.api.Test;

class CareGoalTest {
    private static CareGoal sample() {
        return CareGoal.register(
                CarePlanId.generate(),
                "Sample",
                LocalDate.of(2000, 1, 1),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                "Sample",
                CareGoalStatusId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        CareGoal aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        CareGoalRegisteredEvent event = assertInstanceOf(CareGoalRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        CareGoal aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                CarePlanId.generate(),
                "Sample",
                LocalDate.of(2000, 1, 1),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                "Sample",
                CareGoalStatusId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        CareGoalUpdatedEvent event = assertInstanceOf(CareGoalUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        CareGoal original = sample();
        CareGoal restored = CareGoal.restore(
                original.id(),
                original.carePlanId(),
                original.goalDescription(),
                original.targetDate(),
                original.achievedAt(),
                original.progressNotes(),
                original.careGoalStatusId(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
