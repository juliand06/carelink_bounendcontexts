package com.carelink.domain.common.exception;

import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;

public class HealthRecordNotFoundException extends RuntimeException {
    public HealthRecordNotFoundException(HealthRecordId id) {
        super("HealthRecord not found with id: " + id.value());
    }
}
