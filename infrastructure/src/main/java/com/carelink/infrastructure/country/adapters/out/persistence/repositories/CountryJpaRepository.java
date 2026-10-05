package com.carelink.infrastructure.country.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.country.adapters.out.persistence.entity.CountryJpaEntity;

public interface CountryJpaRepository extends JpaRepository<CountryJpaEntity, UUID> {
}
