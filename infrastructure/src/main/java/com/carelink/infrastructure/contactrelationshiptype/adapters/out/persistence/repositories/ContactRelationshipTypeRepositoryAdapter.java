package com.carelink.infrastructure.contactrelationshiptype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.contactrelationshiptype.model.aggregate.ContactRelationshipType;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;
import com.carelink.domain.contactrelationshiptype.port.repository.ContactRelationshipTypeRepository;
import com.carelink.infrastructure.contactrelationshiptype.adapters.out.persistence.entity.ContactRelationshipTypeJpaEntity;
import com.carelink.infrastructure.contactrelationshiptype.adapters.out.persistence.mappers.ContactRelationshipTypePersistenceMapper;

public class ContactRelationshipTypeRepositoryAdapter implements ContactRelationshipTypeRepository {
    private final ContactRelationshipTypeJpaRepository jpaRepository;
    private final ContactRelationshipTypePersistenceMapper mapper;

    public ContactRelationshipTypeRepositoryAdapter(ContactRelationshipTypeJpaRepository jpaRepository, ContactRelationshipTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ContactRelationshipType save(ContactRelationshipType aggregate) {
        ContactRelationshipTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ContactRelationshipType> findById(ContactRelationshipTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ContactRelationshipType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ContactRelationshipType aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
