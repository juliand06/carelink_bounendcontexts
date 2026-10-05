package com.carelink.infrastructure.client.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.client.model.aggregate.Client;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.client.port.repository.ClientRepository;
import com.carelink.infrastructure.client.adapters.out.persistence.entity.ClientJpaEntity;
import com.carelink.infrastructure.client.adapters.out.persistence.mappers.ClientPersistenceMapper;

public class ClientRepositoryAdapter implements ClientRepository {
    private final ClientJpaRepository jpaRepository;
    private final ClientPersistenceMapper mapper;

    public ClientRepositoryAdapter(ClientJpaRepository jpaRepository, ClientPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Client save(Client aggregate) {
        ClientJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Client> findById(ClientId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Client> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Client aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
