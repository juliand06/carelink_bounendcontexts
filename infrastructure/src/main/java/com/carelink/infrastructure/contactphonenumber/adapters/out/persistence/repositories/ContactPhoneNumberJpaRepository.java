package com.carelink.infrastructure.contactphonenumber.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.contactphonenumber.adapters.out.persistence.entity.ContactPhoneNumberJpaEntity;

public interface ContactPhoneNumberJpaRepository extends JpaRepository<ContactPhoneNumberJpaEntity, UUID> {
}
