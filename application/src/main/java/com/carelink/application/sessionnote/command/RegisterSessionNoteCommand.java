package com.carelink.application.sessionnote.command;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record RegisterSessionNoteCommand(
        AppointmentId appointmentId,
        SpecialistId specialistId,
        String subjective,
        String objective,
        String assessment,
        String plan,
        String supplementaryNotes,
        LocalDateTime signedAt
) {
    public RegisterSessionNoteCommand {
        Objects.requireNonNull(appointmentId, "appointmentId must not be null");
        Objects.requireNonNull(specialistId, "specialistId must not be null");
    }
}
