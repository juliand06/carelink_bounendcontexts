package com.carelink.domain.common.exception;

import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;

public class HealthRecordStatusNotFoundException extends RuntimeException {
    public HealthRecordStatusNotFoundException(HealthRecordStatusId id) {
        super("HealthRecordStatus not found with id: " + id.value());
    }
}
