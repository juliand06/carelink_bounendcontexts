package com.carelink.application.handoffassignment.command;

import java.util.Objects;

import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffassignment.model.valueobject.HandoffAssignmentId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record UpdateHandoffAssignmentCommand(
        HandoffAssignmentId id,
        HandoffId handoffId,
        SpecialistId specialistId
) {
    public UpdateHandoffAssignmentCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(handoffId, "handoffId must not be null");
        Objects.requireNonNull(specialistId, "specialistId must not be null");
    }
}
