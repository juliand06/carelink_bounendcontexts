package com.carelink.infrastructure.contactemailaddress.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.contactemailaddress.adapters.out.persistence.entity.ContactEmailAddressJpaEntity;

public interface ContactEmailAddressJpaRepository extends JpaRepository<ContactEmailAddressJpaEntity, UUID> {
}
