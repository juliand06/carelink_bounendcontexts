package com.carelink.infrastructure.academicprogram.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.academicprogram.model.aggregate.AcademicProgram;
import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.domain.academicprogram.port.repository.AcademicProgramRepository;
import com.carelink.infrastructure.academicprogram.adapters.out.persistence.entity.AcademicProgramJpaEntity;
import com.carelink.infrastructure.academicprogram.adapters.out.persistence.mappers.AcademicProgramPersistenceMapper;

public class AcademicProgramRepositoryAdapter implements AcademicProgramRepository {
    private final AcademicProgramJpaRepository jpaRepository;
    private final AcademicProgramPersistenceMapper mapper;

    public AcademicProgramRepositoryAdapter(AcademicProgramJpaRepository jpaRepository, AcademicProgramPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public AcademicProgram save(AcademicProgram aggregate) {
        AcademicProgramJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<AcademicProgram> findById(AcademicProgramId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<AcademicProgram> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(AcademicProgram aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
