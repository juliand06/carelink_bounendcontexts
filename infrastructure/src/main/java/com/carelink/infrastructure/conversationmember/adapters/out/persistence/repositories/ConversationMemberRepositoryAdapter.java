package com.carelink.infrastructure.conversationmember.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.conversationmember.model.aggregate.ConversationMember;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.conversationmember.port.repository.ConversationMemberRepository;
import com.carelink.infrastructure.conversationmember.adapters.out.persistence.entity.ConversationMemberJpaEntity;
import com.carelink.infrastructure.conversationmember.adapters.out.persistence.mappers.ConversationMemberPersistenceMapper;

public class ConversationMemberRepositoryAdapter implements ConversationMemberRepository {
    private final ConversationMemberJpaRepository jpaRepository;
    private final ConversationMemberPersistenceMapper mapper;

    public ConversationMemberRepositoryAdapter(ConversationMemberJpaRepository jpaRepository, ConversationMemberPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ConversationMember save(ConversationMember aggregate) {
        ConversationMemberJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ConversationMember> findById(ConversationMemberId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ConversationMember> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ConversationMember aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
