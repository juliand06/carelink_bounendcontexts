package com.carelink.infrastructure.messageformat.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.messageformat.model.aggregate.MessageFormat;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;
import com.carelink.domain.messageformat.port.repository.MessageFormatRepository;
import com.carelink.infrastructure.messageformat.adapters.out.persistence.entity.MessageFormatJpaEntity;
import com.carelink.infrastructure.messageformat.adapters.out.persistence.mappers.MessageFormatPersistenceMapper;

public class MessageFormatRepositoryAdapter implements MessageFormatRepository {
    private final MessageFormatJpaRepository jpaRepository;
    private final MessageFormatPersistenceMapper mapper;

    public MessageFormatRepositoryAdapter(MessageFormatJpaRepository jpaRepository, MessageFormatPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public MessageFormat save(MessageFormat aggregate) {
        MessageFormatJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<MessageFormat> findById(MessageFormatId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<MessageFormat> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(MessageFormat aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
