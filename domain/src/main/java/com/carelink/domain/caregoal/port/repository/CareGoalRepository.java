package com.carelink.domain.caregoal.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.caregoal.model.aggregate.CareGoal;
import com.carelink.domain.caregoal.model.valueobject.CareGoalId;

public interface CareGoalRepository {
    CareGoal save(CareGoal aggregate);

    Optional<CareGoal> findById(CareGoalId id);

    List<CareGoal> findAll();

    void delete(CareGoal aggregate);
}
