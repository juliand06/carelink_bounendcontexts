package com.carelink.infrastructure.clientallergy.adapters.out.persistence.mappers;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.clientallergy.model.aggregate.ClientAllergy;
import com.carelink.domain.clientallergy.model.valueobject.ClientAllergyId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.clientallergy.adapters.out.persistence.entity.ClientAllergyJpaEntity;

public class ClientAllergyPersistenceMapper {
    public ClientAllergyJpaEntity toJpa(ClientAllergy domain) {
        if (domain == null) {
            return null;
        }
        ClientAllergyJpaEntity jpa = new ClientAllergyJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setClientId(domain.clientId().value());
        jpa.setAllergen(domain.allergen());
        jpa.setReactionDescription(domain.reactionDescription());
        jpa.setSeverityLevel(domain.severityLevel());
        jpa.setActive(domain.active());
        jpa.setRegisteredAt(domain.registeredAt());
        jpa.setRegisteredBy(domain.registeredBy().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public ClientAllergy toDomain(ClientAllergyJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return ClientAllergy.restore(
                new ClientAllergyId(jpa.getId()),
                new ClientId(jpa.getClientId()),
                jpa.getAllergen(),
                jpa.getReactionDescription(),
                jpa.getSeverityLevel(),
                jpa.getActive(),
                jpa.getRegisteredAt(),
                new SpecialistId(jpa.getRegisteredBy()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
