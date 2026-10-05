package com.carelink.infrastructure.department.adapters.out.persistence.mappers;

import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.department.model.aggregate.Department;
import com.carelink.domain.department.model.valueobject.DepartmentId;
import com.carelink.infrastructure.department.adapters.out.persistence.entity.DepartmentJpaEntity;

public class DepartmentPersistenceMapper {
    public DepartmentJpaEntity toJpa(Department domain) {
        if (domain == null) {
            return null;
        }
        DepartmentJpaEntity jpa = new DepartmentJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setName(domain.name());
        jpa.setIsoCode(domain.isoCode());
        jpa.setNotes(domain.notes());
        jpa.setEnabled(domain.enabled());
        jpa.setCountryId(domain.countryId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public Department toDomain(DepartmentJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return Department.restore(
                new DepartmentId(jpa.getId()),
                jpa.getName(),
                jpa.getIsoCode(),
                jpa.getNotes(),
                jpa.getEnabled(),
                new CountryId(jpa.getCountryId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
