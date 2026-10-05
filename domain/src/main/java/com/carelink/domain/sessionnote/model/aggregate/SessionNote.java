package com.carelink.domain.sessionnote.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.sessionnote.event.SessionNoteRegisteredEvent;
import com.carelink.domain.sessionnote.event.SessionNoteUpdatedEvent;
import com.carelink.domain.sessionnote.model.valueobject.SessionNoteId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public class SessionNote extends AggregateRoot {
    private final SessionNoteId id;
    private AppointmentId appointmentId;
    private SpecialistId specialistId;
    private String subjective;
    private String objective;
    private String assessment;
    private String plan;
    private String supplementaryNotes;
    private LocalDateTime signedAt;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private SessionNote(
            SessionNoteId id,
            AppointmentId appointmentId,
            SpecialistId specialistId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String supplementaryNotes,
            LocalDateTime signedAt,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.appointmentId = Objects.requireNonNull(appointmentId, "appointmentId must not be null");
        this.specialistId = Objects.requireNonNull(specialistId, "specialistId must not be null");
        this.subjective = subjective;
        this.objective = objective;
        this.assessment = assessment;
        this.plan = plan;
        this.supplementaryNotes = supplementaryNotes;
        this.signedAt = signedAt;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static SessionNote register(
            AppointmentId appointmentId,
            SpecialistId specialistId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String supplementaryNotes,
            LocalDateTime signedAt) {
        SessionNoteId id = SessionNoteId.generate();
        LocalDateTime now = LocalDateTime.now();
        SessionNote aggregate = new SessionNote(
                id,
                appointmentId,
                specialistId,
                subjective,
                objective,
                assessment,
                plan,
                supplementaryNotes,
                signedAt,
                now,
                now);
        aggregate.recordEvent(new SessionNoteRegisteredEvent(id, now));
        return aggregate;
    }

    public static SessionNote restore(
            SessionNoteId id,
            AppointmentId appointmentId,
            SpecialistId specialistId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String supplementaryNotes,
            LocalDateTime signedAt,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new SessionNote(
                id,
                appointmentId,
                specialistId,
                subjective,
                objective,
                assessment,
                plan,
                supplementaryNotes,
                signedAt,
                createdAt,
                updatedAt);
    }

    public void update(
            AppointmentId appointmentId,
            SpecialistId specialistId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String supplementaryNotes,
            LocalDateTime signedAt) {
        this.appointmentId = Objects.requireNonNull(appointmentId, "appointmentId must not be null");
        this.specialistId = Objects.requireNonNull(specialistId, "specialistId must not be null");
        this.subjective = subjective;
        this.objective = objective;
        this.assessment = assessment;
        this.plan = plan;
        this.supplementaryNotes = supplementaryNotes;
        this.signedAt = signedAt;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new SessionNoteUpdatedEvent(
                this.id,
                this.appointmentId,
                this.specialistId,
                this.subjective,
                this.objective,
                this.assessment,
                this.plan,
                this.supplementaryNotes,
                this.signedAt,
                now));
    }

    public SessionNoteId id() {
        return id;
    }

    public AppointmentId appointmentId() {
        return appointmentId;
    }

    public SpecialistId specialistId() {
        return specialistId;
    }

    public String subjective() {
        return subjective;
    }

    public String objective() {
        return objective;
    }

    public String assessment() {
        return assessment;
    }

    public String plan() {
        return plan;
    }

    public String supplementaryNotes() {
        return supplementaryNotes;
    }

    public LocalDateTime signedAt() {
        return signedAt;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
