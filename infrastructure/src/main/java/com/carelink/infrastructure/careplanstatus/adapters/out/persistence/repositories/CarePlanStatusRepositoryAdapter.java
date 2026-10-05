package com.carelink.infrastructure.careplanstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.careplanstatus.model.aggregate.CarePlanStatus;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.domain.careplanstatus.port.repository.CarePlanStatusRepository;
import com.carelink.infrastructure.careplanstatus.adapters.out.persistence.entity.CarePlanStatusJpaEntity;
import com.carelink.infrastructure.careplanstatus.adapters.out.persistence.mappers.CarePlanStatusPersistenceMapper;

public class CarePlanStatusRepositoryAdapter implements CarePlanStatusRepository {
    private final CarePlanStatusJpaRepository jpaRepository;
    private final CarePlanStatusPersistenceMapper mapper;

    public CarePlanStatusRepositoryAdapter(CarePlanStatusJpaRepository jpaRepository, CarePlanStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public CarePlanStatus save(CarePlanStatus aggregate) {
        CarePlanStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<CarePlanStatus> findById(CarePlanStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<CarePlanStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(CarePlanStatus aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
