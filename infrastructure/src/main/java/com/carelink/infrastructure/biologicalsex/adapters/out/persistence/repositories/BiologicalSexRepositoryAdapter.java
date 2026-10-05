package com.carelink.infrastructure.biologicalsex.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.biologicalsex.model.aggregate.BiologicalSex;
import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;
import com.carelink.domain.biologicalsex.port.repository.BiologicalSexRepository;
import com.carelink.infrastructure.biologicalsex.adapters.out.persistence.entity.BiologicalSexJpaEntity;
import com.carelink.infrastructure.biologicalsex.adapters.out.persistence.mappers.BiologicalSexPersistenceMapper;

public class BiologicalSexRepositoryAdapter implements BiologicalSexRepository {
    private final BiologicalSexJpaRepository jpaRepository;
    private final BiologicalSexPersistenceMapper mapper;

    public BiologicalSexRepositoryAdapter(BiologicalSexJpaRepository jpaRepository, BiologicalSexPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public BiologicalSex save(BiologicalSex aggregate) {
        BiologicalSexJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<BiologicalSex> findById(BiologicalSexId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<BiologicalSex> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(BiologicalSex aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
