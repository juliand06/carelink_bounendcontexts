package com.carelink.domain.iddocumenttype.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.iddocumenttype.model.aggregate.IdDocumentType;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;

public interface IdDocumentTypeRepository {
    IdDocumentType save(IdDocumentType aggregate);

    Optional<IdDocumentType> findById(IdDocumentTypeId id);

    List<IdDocumentType> findAll();

    void delete(IdDocumentType aggregate);
}
