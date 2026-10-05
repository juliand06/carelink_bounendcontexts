package com.carelink.infrastructure.careplan.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.careplan.model.aggregate.CarePlan;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import com.carelink.domain.careplan.port.repository.CarePlanRepository;
import com.carelink.infrastructure.careplan.adapters.out.persistence.entity.CarePlanJpaEntity;
import com.carelink.infrastructure.careplan.adapters.out.persistence.mappers.CarePlanPersistenceMapper;

public class CarePlanRepositoryAdapter implements CarePlanRepository {
    private final CarePlanJpaRepository jpaRepository;
    private final CarePlanPersistenceMapper mapper;

    public CarePlanRepositoryAdapter(CarePlanJpaRepository jpaRepository, CarePlanPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public CarePlan save(CarePlan aggregate) {
        CarePlanJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<CarePlan> findById(CarePlanId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<CarePlan> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(CarePlan aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
