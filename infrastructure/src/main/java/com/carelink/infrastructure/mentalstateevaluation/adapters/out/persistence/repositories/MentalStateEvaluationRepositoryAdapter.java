package com.carelink.infrastructure.mentalstateevaluation.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.mentalstateevaluation.model.aggregate.MentalStateEvaluation;
import com.carelink.domain.mentalstateevaluation.model.valueobject.MentalStateEvaluationId;
import com.carelink.domain.mentalstateevaluation.port.repository.MentalStateEvaluationRepository;
import com.carelink.infrastructure.mentalstateevaluation.adapters.out.persistence.entity.MentalStateEvaluationJpaEntity;
import com.carelink.infrastructure.mentalstateevaluation.adapters.out.persistence.mappers.MentalStateEvaluationPersistenceMapper;

public class MentalStateEvaluationRepositoryAdapter implements MentalStateEvaluationRepository {
    private final MentalStateEvaluationJpaRepository jpaRepository;
    private final MentalStateEvaluationPersistenceMapper mapper;

    public MentalStateEvaluationRepositoryAdapter(MentalStateEvaluationJpaRepository jpaRepository, MentalStateEvaluationPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public MentalStateEvaluation save(MentalStateEvaluation aggregate) {
        MentalStateEvaluationJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<MentalStateEvaluation> findById(MentalStateEvaluationId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<MentalStateEvaluation> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(MentalStateEvaluation aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
