package com.carelink.infrastructure.client.adapters.out.persistence.mappers;

import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;
import com.carelink.domain.client.model.aggregate.Client;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.client.adapters.out.persistence.entity.ClientJpaEntity;

public class ClientPersistenceMapper {
    public ClientJpaEntity toJpa(Client domain) {
        if (domain == null) {
            return null;
        }
        ClientJpaEntity jpa = new ClientJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setIdDocumentTypeId(domain.idDocumentTypeId().value());
        jpa.setDocumentNumber(domain.documentNumber());
        jpa.setFirstName(domain.firstName());
        jpa.setMiddleName(domain.middleName());
        jpa.setLastName(domain.lastName());
        jpa.setSecondLastName(domain.secondLastName());
        jpa.setBirthDate(domain.birthDate());
        jpa.setBiologicalSexId(domain.biologicalSexId().value());
        jpa.setGenderIdentityId(domain.genderIdentityId().value());
        jpa.setEmail(domain.email());
        jpa.setMobilePhone(domain.mobilePhone());
        jpa.setHomeAddress(domain.homeAddress());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setCreatedBy(domain.createdBy() == null ? null : domain.createdBy().value());
        jpa.setUpdatedAt(domain.updatedAt());
        jpa.setUpdatedBy(domain.updatedBy() == null ? null : domain.updatedBy().value());
        jpa.setMunicipalityId(domain.municipalityId().value());
        return jpa;
    }

    public Client toDomain(ClientJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return Client.restore(
                new ClientId(jpa.getId()),
                new IdDocumentTypeId(jpa.getIdDocumentTypeId()),
                jpa.getDocumentNumber(),
                jpa.getFirstName(),
                jpa.getMiddleName(),
                jpa.getLastName(),
                jpa.getSecondLastName(),
                jpa.getBirthDate(),
                new BiologicalSexId(jpa.getBiologicalSexId()),
                new BiologicalSexId(jpa.getGenderIdentityId()),
                jpa.getEmail(),
                jpa.getMobilePhone(),
                jpa.getHomeAddress(),
                jpa.getActive(),
                jpa.getCreatedAt(),
                jpa.getCreatedBy() == null ? null : new SpecialistId(jpa.getCreatedBy()),
                jpa.getUpdatedAt(),
                jpa.getUpdatedBy() == null ? null : new SpecialistId(jpa.getUpdatedBy()),
                new MunicipalityId(jpa.getMunicipalityId()));
    }
}
