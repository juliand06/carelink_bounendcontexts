package com.carelink.infrastructure.sessionnote.adapters.out.persistence.mappers;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.sessionnote.model.aggregate.SessionNote;
import com.carelink.domain.sessionnote.model.valueobject.SessionNoteId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.sessionnote.adapters.out.persistence.entity.SessionNoteJpaEntity;

public class SessionNotePersistenceMapper {
    public SessionNoteJpaEntity toJpa(SessionNote domain) {
        if (domain == null) {
            return null;
        }
        SessionNoteJpaEntity jpa = new SessionNoteJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setAppointmentId(domain.appointmentId().value());
        jpa.setSpecialistId(domain.specialistId().value());
        jpa.setSubjective(domain.subjective());
        jpa.setObjective(domain.objective());
        jpa.setAssessment(domain.assessment());
        jpa.setPlan(domain.plan());
        jpa.setSupplementaryNotes(domain.supplementaryNotes());
        jpa.setSignedAt(domain.signedAt());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public SessionNote toDomain(SessionNoteJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return SessionNote.restore(
                new SessionNoteId(jpa.getId()),
                new AppointmentId(jpa.getAppointmentId()),
                new SpecialistId(jpa.getSpecialistId()),
                jpa.getSubjective(),
                jpa.getObjective(),
                jpa.getAssessment(),
                jpa.getPlan(),
                jpa.getSupplementaryNotes(),
                jpa.getSignedAt(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
