package com.carelink.domain.common.exception;

import com.carelink.domain.department.model.valueobject.DepartmentId;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(DepartmentId id) {
        super("Department not found with id: " + id.value());
    }
}
