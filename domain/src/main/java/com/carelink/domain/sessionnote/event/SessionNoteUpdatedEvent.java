package com.carelink.domain.sessionnote.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.sessionnote.model.valueobject.SessionNoteId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record SessionNoteUpdatedEvent(
        SessionNoteId id,
        AppointmentId appointmentId,
        SpecialistId specialistId,
        String subjective,
        String objective,
        String assessment,
        String plan,
        String supplementaryNotes,
        LocalDateTime signedAt,
        LocalDateTime occurredOn
) implements DomainEvent {
    public SessionNoteUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(appointmentId, "appointmentId must not be null");
        Objects.requireNonNull(specialistId, "specialistId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
