package com.carelink.domain.municipality.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.municipality.model.aggregate.Municipality;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;

public interface MunicipalityRepository {
    Municipality save(Municipality aggregate);

    Optional<Municipality> findById(MunicipalityId id);

    List<Municipality> findAll();

    void delete(Municipality aggregate);
}
