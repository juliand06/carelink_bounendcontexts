package com.carelink.infrastructure.appointmentstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.appointmentstatus.model.aggregate.AppointmentStatus;
import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;
import com.carelink.domain.appointmentstatus.port.repository.AppointmentStatusRepository;
import com.carelink.infrastructure.appointmentstatus.adapters.out.persistence.entity.AppointmentStatusJpaEntity;
import com.carelink.infrastructure.appointmentstatus.adapters.out.persistence.mappers.AppointmentStatusPersistenceMapper;

public class AppointmentStatusRepositoryAdapter implements AppointmentStatusRepository {
    private final AppointmentStatusJpaRepository jpaRepository;
    private final AppointmentStatusPersistenceMapper mapper;

    public AppointmentStatusRepositoryAdapter(AppointmentStatusJpaRepository jpaRepository, AppointmentStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public AppointmentStatus save(AppointmentStatus aggregate) {
        AppointmentStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<AppointmentStatus> findById(AppointmentStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<AppointmentStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(AppointmentStatus aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
