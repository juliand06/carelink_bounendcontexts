package com.carelink.domain.specialist.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.specialist.model.aggregate.Specialist;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public interface SpecialistRepository {
    Specialist save(Specialist aggregate);

    Optional<Specialist> findById(SpecialistId id);

    List<Specialist> findAll();

    void delete(Specialist aggregate);
}
