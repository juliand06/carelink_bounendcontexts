package com.carelink.domain.department.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.department.model.aggregate.Department;
import com.carelink.domain.department.model.valueobject.DepartmentId;

public interface DepartmentRepository {
    Department save(Department aggregate);

    Optional<Department> findById(DepartmentId id);

    List<Department> findAll();

    void delete(Department aggregate);
}
