package com.carelink.infrastructure.municipality.adapters.out.persistence.mappers;

import com.carelink.domain.department.model.valueobject.DepartmentId;
import com.carelink.domain.municipality.model.aggregate.Municipality;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.infrastructure.municipality.adapters.out.persistence.entity.MunicipalityJpaEntity;

public class MunicipalityPersistenceMapper {
    public MunicipalityJpaEntity toJpa(Municipality domain) {
        if (domain == null) {
            return null;
        }
        MunicipalityJpaEntity jpa = new MunicipalityJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setName(domain.name());
        jpa.setIsoCode(domain.isoCode());
        jpa.setNotes(domain.notes());
        jpa.setEnabled(domain.enabled());
        jpa.setDepartmentId(domain.departmentId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public Municipality toDomain(MunicipalityJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return Municipality.restore(
                new MunicipalityId(jpa.getId()),
                jpa.getName(),
                jpa.getIsoCode(),
                jpa.getNotes(),
                jpa.getEnabled(),
                new DepartmentId(jpa.getDepartmentId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
