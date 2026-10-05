package com.carelink.infrastructure.iddocumenttype.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.iddocumenttype.adapters.out.persistence.entity.IdDocumentTypeJpaEntity;

public interface IdDocumentTypeJpaRepository extends JpaRepository<IdDocumentTypeJpaEntity, UUID> {
}
