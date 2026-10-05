package com.carelink.infrastructure.department.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.department.model.aggregate.Department;
import com.carelink.domain.department.model.valueobject.DepartmentId;
import com.carelink.domain.department.port.repository.DepartmentRepository;
import com.carelink.infrastructure.department.adapters.out.persistence.entity.DepartmentJpaEntity;
import com.carelink.infrastructure.department.adapters.out.persistence.mappers.DepartmentPersistenceMapper;

public class DepartmentRepositoryAdapter implements DepartmentRepository {
    private final DepartmentJpaRepository jpaRepository;
    private final DepartmentPersistenceMapper mapper;

    public DepartmentRepositoryAdapter(DepartmentJpaRepository jpaRepository, DepartmentPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Department save(Department aggregate) {
        DepartmentJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Department> findById(DepartmentId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Department> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Department aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
