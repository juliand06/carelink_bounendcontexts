package com.carelink.application.caregoal.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.caregoal.model.valueobject.CareGoalId;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;

public record UpdateCareGoalCommand(
        CareGoalId id,
        CarePlanId carePlanId,
        String goalDescription,
        LocalDate targetDate,
        LocalDateTime achievedAt,
        String progressNotes,
        CareGoalStatusId careGoalStatusId
) {
    public UpdateCareGoalCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(carePlanId, "carePlanId must not be null");
        Objects.requireNonNull(goalDescription, "goalDescription must not be null");
        Objects.requireNonNull(careGoalStatusId, "careGoalStatusId must not be null");
    }
}
