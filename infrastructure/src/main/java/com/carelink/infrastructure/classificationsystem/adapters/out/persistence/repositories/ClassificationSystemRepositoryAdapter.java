package com.carelink.infrastructure.classificationsystem.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.classificationsystem.model.aggregate.ClassificationSystem;
import com.carelink.domain.classificationsystem.model.valueobject.ClassificationSystemId;
import com.carelink.domain.classificationsystem.port.repository.ClassificationSystemRepository;
import com.carelink.infrastructure.classificationsystem.adapters.out.persistence.entity.ClassificationSystemJpaEntity;
import com.carelink.infrastructure.classificationsystem.adapters.out.persistence.mappers.ClassificationSystemPersistenceMapper;

public class ClassificationSystemRepositoryAdapter implements ClassificationSystemRepository {
    private final ClassificationSystemJpaRepository jpaRepository;
    private final ClassificationSystemPersistenceMapper mapper;

    public ClassificationSystemRepositoryAdapter(ClassificationSystemJpaRepository jpaRepository, ClassificationSystemPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ClassificationSystem save(ClassificationSystem aggregate) {
        ClassificationSystemJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ClassificationSystem> findById(ClassificationSystemId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ClassificationSystem> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ClassificationSystem aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
