package com.carelink.infrastructure.drugadministrationroute.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.drugadministrationroute.model.aggregate.DrugAdministrationRoute;
import com.carelink.domain.drugadministrationroute.model.valueobject.DrugAdministrationRouteId;
import com.carelink.domain.drugadministrationroute.port.repository.DrugAdministrationRouteRepository;
import com.carelink.infrastructure.drugadministrationroute.adapters.out.persistence.entity.DrugAdministrationRouteJpaEntity;
import com.carelink.infrastructure.drugadministrationroute.adapters.out.persistence.mappers.DrugAdministrationRoutePersistenceMapper;

public class DrugAdministrationRouteRepositoryAdapter implements DrugAdministrationRouteRepository {
    private final DrugAdministrationRouteJpaRepository jpaRepository;
    private final DrugAdministrationRoutePersistenceMapper mapper;

    public DrugAdministrationRouteRepositoryAdapter(DrugAdministrationRouteJpaRepository jpaRepository, DrugAdministrationRoutePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DrugAdministrationRoute save(DrugAdministrationRoute aggregate) {
        DrugAdministrationRouteJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<DrugAdministrationRoute> findById(DrugAdministrationRouteId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<DrugAdministrationRoute> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(DrugAdministrationRoute aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
