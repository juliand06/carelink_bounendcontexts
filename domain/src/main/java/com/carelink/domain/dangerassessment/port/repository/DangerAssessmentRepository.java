package com.carelink.domain.dangerassessment.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.dangerassessment.model.aggregate.DangerAssessment;
import com.carelink.domain.dangerassessment.model.valueobject.DangerAssessmentId;

public interface DangerAssessmentRepository {
    DangerAssessment save(DangerAssessment aggregate);

    Optional<DangerAssessment> findById(DangerAssessmentId id);

    List<DangerAssessment> findAll();

    void delete(DangerAssessment aggregate);
}
