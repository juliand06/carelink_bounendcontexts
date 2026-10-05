package com.carelink.domain.caregoal.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.caregoal.model.valueobject.CareGoalId;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import com.carelink.domain.common.event.DomainEvent;

public record CareGoalUpdatedEvent(
        CareGoalId id,
        CarePlanId carePlanId,
        String goalDescription,
        LocalDate targetDate,
        LocalDateTime achievedAt,
        String progressNotes,
        CareGoalStatusId careGoalStatusId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public CareGoalUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(carePlanId, "carePlanId must not be null");
        Objects.requireNonNull(goalDescription, "goalDescription must not be null");
        Objects.requireNonNull(careGoalStatusId, "careGoalStatusId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
