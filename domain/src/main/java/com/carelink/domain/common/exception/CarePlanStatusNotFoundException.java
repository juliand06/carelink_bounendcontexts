package com.carelink.domain.common.exception;

import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;

public class CarePlanStatusNotFoundException extends RuntimeException {
    public CarePlanStatusNotFoundException(CarePlanStatusId id) {
        super("CarePlanStatus not found with id: " + id.value());
    }
}
