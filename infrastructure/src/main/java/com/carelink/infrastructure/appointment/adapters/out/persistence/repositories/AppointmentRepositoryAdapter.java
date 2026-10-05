package com.carelink.infrastructure.appointment.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.appointment.model.aggregate.Appointment;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.appointment.port.repository.AppointmentRepository;
import com.carelink.infrastructure.appointment.adapters.out.persistence.entity.AppointmentJpaEntity;
import com.carelink.infrastructure.appointment.adapters.out.persistence.mappers.AppointmentPersistenceMapper;

public class AppointmentRepositoryAdapter implements AppointmentRepository {
    private final AppointmentJpaRepository jpaRepository;
    private final AppointmentPersistenceMapper mapper;

    public AppointmentRepositoryAdapter(AppointmentJpaRepository jpaRepository, AppointmentPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Appointment save(Appointment aggregate) {
        AppointmentJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Appointment> findById(AppointmentId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Appointment> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Appointment aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
