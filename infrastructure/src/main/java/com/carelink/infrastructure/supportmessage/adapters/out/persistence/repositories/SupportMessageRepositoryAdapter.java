package com.carelink.infrastructure.supportmessage.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.supportmessage.model.aggregate.SupportMessage;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;
import com.carelink.domain.supportmessage.port.repository.SupportMessageRepository;
import com.carelink.infrastructure.supportmessage.adapters.out.persistence.entity.SupportMessageJpaEntity;
import com.carelink.infrastructure.supportmessage.adapters.out.persistence.mappers.SupportMessagePersistenceMapper;

public class SupportMessageRepositoryAdapter implements SupportMessageRepository {
    private final SupportMessageJpaRepository jpaRepository;
    private final SupportMessagePersistenceMapper mapper;

    public SupportMessageRepositoryAdapter(SupportMessageJpaRepository jpaRepository, SupportMessagePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SupportMessage save(SupportMessage aggregate) {
        SupportMessageJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<SupportMessage> findById(SupportMessageId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<SupportMessage> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(SupportMessage aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
