package com.carelink.infrastructure.supportconversation.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.supportconversation.model.aggregate.SupportConversation;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportconversation.port.repository.SupportConversationRepository;
import com.carelink.infrastructure.supportconversation.adapters.out.persistence.entity.SupportConversationJpaEntity;
import com.carelink.infrastructure.supportconversation.adapters.out.persistence.mappers.SupportConversationPersistenceMapper;

public class SupportConversationRepositoryAdapter implements SupportConversationRepository {
    private final SupportConversationJpaRepository jpaRepository;
    private final SupportConversationPersistenceMapper mapper;

    public SupportConversationRepositoryAdapter(SupportConversationJpaRepository jpaRepository, SupportConversationPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SupportConversation save(SupportConversation aggregate) {
        SupportConversationJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<SupportConversation> findById(SupportConversationId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<SupportConversation> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(SupportConversation aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
