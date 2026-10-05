package com.carelink.application.handoffassignment.command;

import java.util.Objects;

import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record RegisterHandoffAssignmentCommand(
        HandoffId handoffId,
        SpecialistId specialistId
) {
    public RegisterHandoffAssignmentCommand {
        Objects.requireNonNull(handoffId, "handoffId must not be null");
        Objects.requireNonNull(specialistId, "specialistId must not be null");
    }
}
