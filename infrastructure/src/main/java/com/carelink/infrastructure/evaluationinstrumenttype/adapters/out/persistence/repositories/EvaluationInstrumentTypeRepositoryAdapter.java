package com.carelink.infrastructure.evaluationinstrumenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.evaluationinstrumenttype.model.aggregate.EvaluationInstrumentType;
import com.carelink.domain.evaluationinstrumenttype.model.valueobject.EvaluationInstrumentTypeId;
import com.carelink.domain.evaluationinstrumenttype.port.repository.EvaluationInstrumentTypeRepository;
import com.carelink.infrastructure.evaluationinstrumenttype.adapters.out.persistence.entity.EvaluationInstrumentTypeJpaEntity;
import com.carelink.infrastructure.evaluationinstrumenttype.adapters.out.persistence.mappers.EvaluationInstrumentTypePersistenceMapper;

public class EvaluationInstrumentTypeRepositoryAdapter implements EvaluationInstrumentTypeRepository {
    private final EvaluationInstrumentTypeJpaRepository jpaRepository;
    private final EvaluationInstrumentTypePersistenceMapper mapper;

    public EvaluationInstrumentTypeRepositoryAdapter(EvaluationInstrumentTypeJpaRepository jpaRepository, EvaluationInstrumentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EvaluationInstrumentType save(EvaluationInstrumentType aggregate) {
        EvaluationInstrumentTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<EvaluationInstrumentType> findById(EvaluationInstrumentTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<EvaluationInstrumentType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(EvaluationInstrumentType aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
