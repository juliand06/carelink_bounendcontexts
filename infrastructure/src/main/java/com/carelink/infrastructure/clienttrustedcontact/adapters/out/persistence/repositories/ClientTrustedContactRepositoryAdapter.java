package com.carelink.infrastructure.clienttrustedcontact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.clienttrustedcontact.model.aggregate.ClientTrustedContact;
import com.carelink.domain.clienttrustedcontact.model.valueobject.ClientTrustedContactId;
import com.carelink.domain.clienttrustedcontact.port.repository.ClientTrustedContactRepository;
import com.carelink.infrastructure.clienttrustedcontact.adapters.out.persistence.entity.ClientTrustedContactJpaEntity;
import com.carelink.infrastructure.clienttrustedcontact.adapters.out.persistence.mappers.ClientTrustedContactPersistenceMapper;

public class ClientTrustedContactRepositoryAdapter implements ClientTrustedContactRepository {
    private final ClientTrustedContactJpaRepository jpaRepository;
    private final ClientTrustedContactPersistenceMapper mapper;

    public ClientTrustedContactRepositoryAdapter(ClientTrustedContactJpaRepository jpaRepository, ClientTrustedContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ClientTrustedContact save(ClientTrustedContact aggregate) {
        ClientTrustedContactJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ClientTrustedContact> findById(ClientTrustedContactId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ClientTrustedContact> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ClientTrustedContact aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
