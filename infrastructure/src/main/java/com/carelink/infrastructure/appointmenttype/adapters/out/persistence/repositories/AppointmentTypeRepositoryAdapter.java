package com.carelink.infrastructure.appointmenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.appointmenttype.model.aggregate.AppointmentType;
import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;
import com.carelink.domain.appointmenttype.port.repository.AppointmentTypeRepository;
import com.carelink.infrastructure.appointmenttype.adapters.out.persistence.entity.AppointmentTypeJpaEntity;
import com.carelink.infrastructure.appointmenttype.adapters.out.persistence.mappers.AppointmentTypePersistenceMapper;

public class AppointmentTypeRepositoryAdapter implements AppointmentTypeRepository {
    private final AppointmentTypeJpaRepository jpaRepository;
    private final AppointmentTypePersistenceMapper mapper;

    public AppointmentTypeRepositoryAdapter(AppointmentTypeJpaRepository jpaRepository, AppointmentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public AppointmentType save(AppointmentType aggregate) {
        AppointmentTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<AppointmentType> findById(AppointmentTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<AppointmentType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(AppointmentType aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
