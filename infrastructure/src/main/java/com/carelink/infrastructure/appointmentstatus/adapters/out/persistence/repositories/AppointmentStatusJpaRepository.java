package com.carelink.infrastructure.appointmentstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.appointmentstatus.adapters.out.persistence.entity.AppointmentStatusJpaEntity;

public interface AppointmentStatusJpaRepository extends JpaRepository<AppointmentStatusJpaEntity, UUID> {
}
