package com.carelink.domain.common.exception;

import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;

public class CareGoalStatusNotFoundException extends RuntimeException {
    public CareGoalStatusNotFoundException(CareGoalStatusId id) {
        super("CareGoalStatus not found with id: " + id.value());
    }
}
