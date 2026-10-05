package com.carelink.infrastructure.contactphonenumber.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.contactphonenumber.model.aggregate.ContactPhoneNumber;
import com.carelink.domain.contactphonenumber.model.valueobject.ContactPhoneNumberId;
import com.carelink.domain.contactphonenumber.port.repository.ContactPhoneNumberRepository;
import com.carelink.infrastructure.contactphonenumber.adapters.out.persistence.entity.ContactPhoneNumberJpaEntity;
import com.carelink.infrastructure.contactphonenumber.adapters.out.persistence.mappers.ContactPhoneNumberPersistenceMapper;

public class ContactPhoneNumberRepositoryAdapter implements ContactPhoneNumberRepository {
    private final ContactPhoneNumberJpaRepository jpaRepository;
    private final ContactPhoneNumberPersistenceMapper mapper;

    public ContactPhoneNumberRepositoryAdapter(ContactPhoneNumberJpaRepository jpaRepository, ContactPhoneNumberPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ContactPhoneNumber save(ContactPhoneNumber aggregate) {
        ContactPhoneNumberJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ContactPhoneNumber> findById(ContactPhoneNumberId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ContactPhoneNumber> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ContactPhoneNumber aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
