package com.carelink.domain.specialistrole.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.specialistrole.model.aggregate.SpecialistRole;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;

public interface SpecialistRoleRepository {
    SpecialistRole save(SpecialistRole aggregate);

    Optional<SpecialistRole> findById(SpecialistRoleId id);

    List<SpecialistRole> findAll();

    void delete(SpecialistRole aggregate);
}
