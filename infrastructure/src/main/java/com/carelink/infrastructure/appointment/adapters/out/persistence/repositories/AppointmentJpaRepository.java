package com.carelink.infrastructure.appointment.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.appointment.adapters.out.persistence.entity.AppointmentJpaEntity;

public interface AppointmentJpaRepository extends JpaRepository<AppointmentJpaEntity, UUID> {
}
