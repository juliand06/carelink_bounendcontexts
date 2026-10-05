package com.carelink.infrastructure.clientallergy.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.clientallergy.model.aggregate.ClientAllergy;
import com.carelink.domain.clientallergy.model.valueobject.ClientAllergyId;
import com.carelink.domain.clientallergy.port.repository.ClientAllergyRepository;
import com.carelink.infrastructure.clientallergy.adapters.out.persistence.entity.ClientAllergyJpaEntity;
import com.carelink.infrastructure.clientallergy.adapters.out.persistence.mappers.ClientAllergyPersistenceMapper;

public class ClientAllergyRepositoryAdapter implements ClientAllergyRepository {
    private final ClientAllergyJpaRepository jpaRepository;
    private final ClientAllergyPersistenceMapper mapper;

    public ClientAllergyRepositoryAdapter(ClientAllergyJpaRepository jpaRepository, ClientAllergyPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ClientAllergy save(ClientAllergy aggregate) {
        ClientAllergyJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ClientAllergy> findById(ClientAllergyId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ClientAllergy> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ClientAllergy aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
