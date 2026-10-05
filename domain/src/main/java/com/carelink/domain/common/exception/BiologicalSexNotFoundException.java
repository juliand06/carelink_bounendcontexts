package com.carelink.domain.common.exception;

import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;

public class BiologicalSexNotFoundException extends RuntimeException {
    public BiologicalSexNotFoundException(BiologicalSexId id) {
        super("BiologicalSex not found with id: " + id.value());
    }
}
