package com.carelink.infrastructure.appointmentmodality.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.appointmentmodality.model.aggregate.AppointmentModality;
import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;
import com.carelink.domain.appointmentmodality.port.repository.AppointmentModalityRepository;
import com.carelink.infrastructure.appointmentmodality.adapters.out.persistence.entity.AppointmentModalityJpaEntity;
import com.carelink.infrastructure.appointmentmodality.adapters.out.persistence.mappers.AppointmentModalityPersistenceMapper;

public class AppointmentModalityRepositoryAdapter implements AppointmentModalityRepository {
    private final AppointmentModalityJpaRepository jpaRepository;
    private final AppointmentModalityPersistenceMapper mapper;

    public AppointmentModalityRepositoryAdapter(AppointmentModalityJpaRepository jpaRepository, AppointmentModalityPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public AppointmentModality save(AppointmentModality aggregate) {
        AppointmentModalityJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<AppointmentModality> findById(AppointmentModalityId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<AppointmentModality> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(AppointmentModality aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
