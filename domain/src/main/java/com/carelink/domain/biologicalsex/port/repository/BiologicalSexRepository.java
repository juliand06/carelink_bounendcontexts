package com.carelink.domain.biologicalsex.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.biologicalsex.model.aggregate.BiologicalSex;
import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;

public interface BiologicalSexRepository {
    BiologicalSex save(BiologicalSex aggregate);

    Optional<BiologicalSex> findById(BiologicalSexId id);

    List<BiologicalSex> findAll();

    void delete(BiologicalSex aggregate);
}
