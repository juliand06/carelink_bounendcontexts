package com.carelink.infrastructure.sessionnote.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.sessionnote.model.aggregate.SessionNote;
import com.carelink.domain.sessionnote.model.valueobject.SessionNoteId;
import com.carelink.domain.sessionnote.port.repository.SessionNoteRepository;
import com.carelink.infrastructure.sessionnote.adapters.out.persistence.entity.SessionNoteJpaEntity;
import com.carelink.infrastructure.sessionnote.adapters.out.persistence.mappers.SessionNotePersistenceMapper;

public class SessionNoteRepositoryAdapter implements SessionNoteRepository {
    private final SessionNoteJpaRepository jpaRepository;
    private final SessionNotePersistenceMapper mapper;

    public SessionNoteRepositoryAdapter(SessionNoteJpaRepository jpaRepository, SessionNotePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SessionNote save(SessionNote aggregate) {
        SessionNoteJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<SessionNote> findById(SessionNoteId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<SessionNote> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(SessionNote aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
