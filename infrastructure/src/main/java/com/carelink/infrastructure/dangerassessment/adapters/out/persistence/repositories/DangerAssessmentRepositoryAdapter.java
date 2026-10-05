package com.carelink.infrastructure.dangerassessment.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.dangerassessment.model.aggregate.DangerAssessment;
import com.carelink.domain.dangerassessment.model.valueobject.DangerAssessmentId;
import com.carelink.domain.dangerassessment.port.repository.DangerAssessmentRepository;
import com.carelink.infrastructure.dangerassessment.adapters.out.persistence.entity.DangerAssessmentJpaEntity;
import com.carelink.infrastructure.dangerassessment.adapters.out.persistence.mappers.DangerAssessmentPersistenceMapper;

public class DangerAssessmentRepositoryAdapter implements DangerAssessmentRepository {
    private final DangerAssessmentJpaRepository jpaRepository;
    private final DangerAssessmentPersistenceMapper mapper;

    public DangerAssessmentRepositoryAdapter(DangerAssessmentJpaRepository jpaRepository, DangerAssessmentPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DangerAssessment save(DangerAssessment aggregate) {
        DangerAssessmentJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<DangerAssessment> findById(DangerAssessmentId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<DangerAssessment> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(DangerAssessment aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
