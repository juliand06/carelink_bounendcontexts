package com.carelink.domain.informedconsenttype.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.informedconsenttype.model.aggregate.InformedConsentType;
import com.carelink.domain.informedconsenttype.model.valueobject.InformedConsentTypeId;

public interface InformedConsentTypeRepository {
    InformedConsentType save(InformedConsentType aggregate);

    Optional<InformedConsentType> findById(InformedConsentTypeId id);

    List<InformedConsentType> findAll();

    void delete(InformedConsentType aggregate);
}
