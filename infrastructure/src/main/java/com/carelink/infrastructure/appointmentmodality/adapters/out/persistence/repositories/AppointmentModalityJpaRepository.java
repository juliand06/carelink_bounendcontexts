package com.carelink.infrastructure.appointmentmodality.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.appointmentmodality.adapters.out.persistence.entity.AppointmentModalityJpaEntity;

public interface AppointmentModalityJpaRepository extends JpaRepository<AppointmentModalityJpaEntity, UUID> {
}
