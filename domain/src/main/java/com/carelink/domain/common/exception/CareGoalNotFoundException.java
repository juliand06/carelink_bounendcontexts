package com.carelink.domain.common.exception;

import com.carelink.domain.caregoal.model.valueobject.CareGoalId;

public class CareGoalNotFoundException extends RuntimeException {
    public CareGoalNotFoundException(CareGoalId id) {
        super("CareGoal not found with id: " + id.value());
    }
}
