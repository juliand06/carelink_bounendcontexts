package com.carelink.domain.caregoalstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.caregoalstatus.model.aggregate.CareGoalStatus;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;

public interface CareGoalStatusRepository {
    CareGoalStatus save(CareGoalStatus aggregate);

    Optional<CareGoalStatus> findById(CareGoalStatusId id);

    List<CareGoalStatus> findAll();

    void delete(CareGoalStatus aggregate);
}
