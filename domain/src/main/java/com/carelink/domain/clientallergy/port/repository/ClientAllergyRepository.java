package com.carelink.domain.clientallergy.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.clientallergy.model.aggregate.ClientAllergy;
import com.carelink.domain.clientallergy.model.valueobject.ClientAllergyId;

public interface ClientAllergyRepository {
    ClientAllergy save(ClientAllergy aggregate);

    Optional<ClientAllergy> findById(ClientAllergyId id);

    List<ClientAllergy> findAll();

    void delete(ClientAllergy aggregate);
}
