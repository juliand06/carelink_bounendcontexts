package com.carelink.domain.mentalstateevaluation.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.mentalstateevaluation.model.aggregate.MentalStateEvaluation;
import com.carelink.domain.mentalstateevaluation.model.valueobject.MentalStateEvaluationId;

public interface MentalStateEvaluationRepository {
    MentalStateEvaluation save(MentalStateEvaluation aggregate);

    Optional<MentalStateEvaluation> findById(MentalStateEvaluationId id);

    List<MentalStateEvaluation> findAll();

    void delete(MentalStateEvaluation aggregate);
}
