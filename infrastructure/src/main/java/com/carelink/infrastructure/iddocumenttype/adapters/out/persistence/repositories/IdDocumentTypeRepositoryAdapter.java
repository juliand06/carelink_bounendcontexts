package com.carelink.infrastructure.iddocumenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.iddocumenttype.model.aggregate.IdDocumentType;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.iddocumenttype.port.repository.IdDocumentTypeRepository;
import com.carelink.infrastructure.iddocumenttype.adapters.out.persistence.entity.IdDocumentTypeJpaEntity;
import com.carelink.infrastructure.iddocumenttype.adapters.out.persistence.mappers.IdDocumentTypePersistenceMapper;

public class IdDocumentTypeRepositoryAdapter implements IdDocumentTypeRepository {
    private final IdDocumentTypeJpaRepository jpaRepository;
    private final IdDocumentTypePersistenceMapper mapper;

    public IdDocumentTypeRepositoryAdapter(IdDocumentTypeJpaRepository jpaRepository, IdDocumentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public IdDocumentType save(IdDocumentType aggregate) {
        IdDocumentTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<IdDocumentType> findById(IdDocumentTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<IdDocumentType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(IdDocumentType aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
