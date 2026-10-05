package com.carelink.domain.common.exception;

import com.carelink.domain.careplan.model.valueobject.CarePlanId;

public class CarePlanNotFoundException extends RuntimeException {
    public CarePlanNotFoundException(CarePlanId id) {
        super("CarePlan not found with id: " + id.value());
    }
}
