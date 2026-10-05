package com.carelink.infrastructure.specialistcredential.adapters.out.persistence.mappers;

import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialistcredential.model.aggregate.SpecialistCredential;
import com.carelink.domain.specialistcredential.model.valueobject.SpecialistCredentialId;
import com.carelink.infrastructure.specialistcredential.adapters.out.persistence.entity.SpecialistCredentialJpaEntity;

public class SpecialistCredentialPersistenceMapper {
    public SpecialistCredentialJpaEntity toJpa(SpecialistCredential domain) {
        if (domain == null) {
            return null;
        }
        SpecialistCredentialJpaEntity jpa = new SpecialistCredentialJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setAcademicProgramId(domain.academicProgramId().value());
        jpa.setSpecialistId(domain.specialistId().value());
        jpa.setDegreeTitle(domain.degreeTitle());
        jpa.setInstitution(domain.institution());
        jpa.setIsVerified(domain.isVerified());
        jpa.setResolutionCode(domain.resolutionCode());
        jpa.setCountryId(domain.countryId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public SpecialistCredential toDomain(SpecialistCredentialJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return SpecialistCredential.restore(
                new SpecialistCredentialId(jpa.getId()),
                new AcademicProgramId(jpa.getAcademicProgramId()),
                new SpecialistId(jpa.getSpecialistId()),
                jpa.getDegreeTitle(),
                jpa.getInstitution(),
                jpa.getIsVerified(),
                jpa.getResolutionCode(),
                new CountryId(jpa.getCountryId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
