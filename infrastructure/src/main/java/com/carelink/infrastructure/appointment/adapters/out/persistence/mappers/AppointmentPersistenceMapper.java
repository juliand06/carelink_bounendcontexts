package com.carelink.infrastructure.appointment.adapters.out.persistence.mappers;

import com.carelink.domain.appointment.model.aggregate.Appointment;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;
import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;
import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.appointment.adapters.out.persistence.entity.AppointmentJpaEntity;

public class AppointmentPersistenceMapper {
    public AppointmentJpaEntity toJpa(Appointment domain) {
        if (domain == null) {
            return null;
        }
        AppointmentJpaEntity jpa = new AppointmentJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setHealthRecordId(domain.healthRecordId().value());
        jpa.setSpecialistId(domain.specialistId().value());
        jpa.setAppointmentTypeId(domain.appointmentTypeId().value());
        jpa.setStartedAt(domain.startedAt());
        jpa.setEndedAt(domain.endedAt());
        jpa.setChiefComplaint(domain.chiefComplaint());
        jpa.setCurrentCondition(domain.currentCondition());
        jpa.setModalityId(domain.modalityId().value());
        jpa.setStatusId(domain.statusId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setCreatedBy(domain.createdBy().value());
        jpa.setUpdatedAt(domain.updatedAt());
        jpa.setUpdatedBy(domain.updatedBy() == null ? null : domain.updatedBy().value());
        return jpa;
    }

    public Appointment toDomain(AppointmentJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return Appointment.restore(
                new AppointmentId(jpa.getId()),
                new HealthRecordId(jpa.getHealthRecordId()),
                new SpecialistId(jpa.getSpecialistId()),
                new AppointmentTypeId(jpa.getAppointmentTypeId()),
                jpa.getStartedAt(),
                jpa.getEndedAt(),
                jpa.getChiefComplaint(),
                jpa.getCurrentCondition(),
                new AppointmentModalityId(jpa.getModalityId()),
                new AppointmentStatusId(jpa.getStatusId()),
                jpa.getCreatedAt(),
                new SpecialistId(jpa.getCreatedBy()),
                jpa.getUpdatedAt(),
                jpa.getUpdatedBy() == null ? null : new SpecialistId(jpa.getUpdatedBy()));
    }
}
