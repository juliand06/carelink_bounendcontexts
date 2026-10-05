package com.carelink.infrastructure.contactrelationshiptype.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.contactrelationshiptype.adapters.out.persistence.entity.ContactRelationshipTypeJpaEntity;

public interface ContactRelationshipTypeJpaRepository extends JpaRepository<ContactRelationshipTypeJpaEntity, UUID> {
}
