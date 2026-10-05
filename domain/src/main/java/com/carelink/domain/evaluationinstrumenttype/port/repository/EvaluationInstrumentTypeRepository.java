package com.carelink.domain.evaluationinstrumenttype.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.evaluationinstrumenttype.model.aggregate.EvaluationInstrumentType;
import com.carelink.domain.evaluationinstrumenttype.model.valueobject.EvaluationInstrumentTypeId;

public interface EvaluationInstrumentTypeRepository {
    EvaluationInstrumentType save(EvaluationInstrumentType aggregate);

    Optional<EvaluationInstrumentType> findById(EvaluationInstrumentTypeId id);

    List<EvaluationInstrumentType> findAll();

    void delete(EvaluationInstrumentType aggregate);
}
