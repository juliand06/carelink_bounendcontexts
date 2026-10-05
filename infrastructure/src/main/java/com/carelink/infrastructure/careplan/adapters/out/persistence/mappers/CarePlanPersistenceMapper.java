package com.carelink.infrastructure.careplan.adapters.out.persistence.mappers;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.careplan.model.aggregate.CarePlan;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.careplan.adapters.out.persistence.entity.CarePlanJpaEntity;

public class CarePlanPersistenceMapper {
    public CarePlanJpaEntity toJpa(CarePlan domain) {
        if (domain == null) {
            return null;
        }
        CarePlanJpaEntity jpa = new CarePlanJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setAppointmentId(domain.appointmentId().value());
        jpa.setSpecialistId(domain.specialistId().value());
        jpa.setPlanTitle(domain.planTitle());
        jpa.setPlanDescription(domain.planDescription());
        jpa.setStartDate(domain.startDate());
        jpa.setProjectedEndDate(domain.projectedEndDate());
        jpa.setCarePlanStatusId(domain.carePlanStatusId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public CarePlan toDomain(CarePlanJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return CarePlan.restore(
                new CarePlanId(jpa.getId()),
                new AppointmentId(jpa.getAppointmentId()),
                new SpecialistId(jpa.getSpecialistId()),
                jpa.getPlanTitle(),
                jpa.getPlanDescription(),
                jpa.getStartDate(),
                jpa.getProjectedEndDate(),
                new CarePlanStatusId(jpa.getCarePlanStatusId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
