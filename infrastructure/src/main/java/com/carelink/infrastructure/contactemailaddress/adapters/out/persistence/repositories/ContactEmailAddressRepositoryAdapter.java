package com.carelink.infrastructure.contactemailaddress.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.contactemailaddress.model.aggregate.ContactEmailAddress;
import com.carelink.domain.contactemailaddress.model.valueobject.ContactEmailAddressId;
import com.carelink.domain.contactemailaddress.port.repository.ContactEmailAddressRepository;
import com.carelink.infrastructure.contactemailaddress.adapters.out.persistence.entity.ContactEmailAddressJpaEntity;
import com.carelink.infrastructure.contactemailaddress.adapters.out.persistence.mappers.ContactEmailAddressPersistenceMapper;

public class ContactEmailAddressRepositoryAdapter implements ContactEmailAddressRepository {
    private final ContactEmailAddressJpaRepository jpaRepository;
    private final ContactEmailAddressPersistenceMapper mapper;

    public ContactEmailAddressRepositoryAdapter(ContactEmailAddressJpaRepository jpaRepository, ContactEmailAddressPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ContactEmailAddress save(ContactEmailAddress aggregate) {
        ContactEmailAddressJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ContactEmailAddress> findById(ContactEmailAddressId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ContactEmailAddress> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ContactEmailAddress aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
