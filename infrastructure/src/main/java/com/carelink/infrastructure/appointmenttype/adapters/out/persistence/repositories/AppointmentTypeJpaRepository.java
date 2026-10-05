package com.carelink.infrastructure.appointmenttype.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.appointmenttype.adapters.out.persistence.entity.AppointmentTypeJpaEntity;

public interface AppointmentTypeJpaRepository extends JpaRepository<AppointmentTypeJpaEntity, UUID> {
}
