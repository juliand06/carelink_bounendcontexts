package com.carelink.infrastructure.conversationaiconfig.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.conversationaiconfig.model.aggregate.ConversationAiConfig;
import com.carelink.domain.conversationaiconfig.model.valueobject.ConversationAiConfigId;
import com.carelink.domain.conversationaiconfig.port.repository.ConversationAiConfigRepository;
import com.carelink.infrastructure.conversationaiconfig.adapters.out.persistence.entity.ConversationAiConfigJpaEntity;
import com.carelink.infrastructure.conversationaiconfig.adapters.out.persistence.mappers.ConversationAiConfigPersistenceMapper;

public class ConversationAiConfigRepositoryAdapter implements ConversationAiConfigRepository {
    private final ConversationAiConfigJpaRepository jpaRepository;
    private final ConversationAiConfigPersistenceMapper mapper;

    public ConversationAiConfigRepositoryAdapter(ConversationAiConfigJpaRepository jpaRepository, ConversationAiConfigPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ConversationAiConfig save(ConversationAiConfig aggregate) {
        ConversationAiConfigJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ConversationAiConfig> findById(ConversationAiConfigId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ConversationAiConfig> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ConversationAiConfig aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
