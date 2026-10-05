package com.carelink.infrastructure.drugadministrationroute.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.drugadministrationroute.adapters.out.persistence.entity.DrugAdministrationRouteJpaEntity;

public interface DrugAdministrationRouteJpaRepository extends JpaRepository<DrugAdministrationRouteJpaEntity, UUID> {
}
