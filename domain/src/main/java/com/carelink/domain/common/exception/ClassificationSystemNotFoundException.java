package com.carelink.domain.common.exception;

import com.carelink.domain.classificationsystem.model.valueobject.ClassificationSystemId;

public class ClassificationSystemNotFoundException extends RuntimeException {
    public ClassificationSystemNotFoundException(ClassificationSystemId id) {
        super("ClassificationSystem not found with id: " + id.value());
    }
}
