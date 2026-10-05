package com.carelink.infrastructure.supportconversationstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.supportconversationstatus.model.aggregate.SupportConversationStatus;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;
import com.carelink.domain.supportconversationstatus.port.repository.SupportConversationStatusRepository;
import com.carelink.infrastructure.supportconversationstatus.adapters.out.persistence.entity.SupportConversationStatusJpaEntity;
import com.carelink.infrastructure.supportconversationstatus.adapters.out.persistence.mappers.SupportConversationStatusPersistenceMapper;

public class SupportConversationStatusRepositoryAdapter implements SupportConversationStatusRepository {
    private final SupportConversationStatusJpaRepository jpaRepository;
    private final SupportConversationStatusPersistenceMapper mapper;

    public SupportConversationStatusRepositoryAdapter(SupportConversationStatusJpaRepository jpaRepository, SupportConversationStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SupportConversationStatus save(SupportConversationStatus aggregate) {
        SupportConversationStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<SupportConversationStatus> findById(SupportConversationStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<SupportConversationStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(SupportConversationStatus aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
