package com.carelink.infrastructure.specialist.adapters.out.persistence.mappers;

import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.specialist.model.aggregate.Specialist;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;
import com.carelink.infrastructure.specialist.adapters.out.persistence.entity.SpecialistJpaEntity;

public class SpecialistPersistenceMapper {
    public SpecialistJpaEntity toJpa(Specialist domain) {
        if (domain == null) {
            return null;
        }
        SpecialistJpaEntity jpa = new SpecialistJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setIdDocumentTypeId(domain.idDocumentTypeId().value());
        jpa.setDocumentNumber(domain.documentNumber());
        jpa.setFirstName(domain.firstName());
        jpa.setLastName(domain.lastName());
        jpa.setSpecialistRoleId(domain.specialistRoleId().value());
        jpa.setProfessionalLicense(domain.professionalLicense());
        jpa.setActive(domain.active());
        jpa.setMunicipalityId(domain.municipalityId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public Specialist toDomain(SpecialistJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return Specialist.restore(
                new SpecialistId(jpa.getId()),
                new IdDocumentTypeId(jpa.getIdDocumentTypeId()),
                jpa.getDocumentNumber(),
                jpa.getFirstName(),
                jpa.getLastName(),
                new SpecialistRoleId(jpa.getSpecialistRoleId()),
                jpa.getProfessionalLicense(),
                jpa.getActive(),
                new MunicipalityId(jpa.getMunicipalityId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
