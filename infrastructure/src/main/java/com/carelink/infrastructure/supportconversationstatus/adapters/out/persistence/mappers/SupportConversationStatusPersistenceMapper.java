package com.carelink.infrastructure.supportconversationstatus.adapters.out.persistence.mappers;

import com.carelink.domain.supportconversationstatus.model.aggregate.SupportConversationStatus;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;
import com.carelink.infrastructure.supportconversationstatus.adapters.out.persistence.entity.SupportConversationStatusJpaEntity;

public class SupportConversationStatusPersistenceMapper {
    public SupportConversationStatusJpaEntity toJpa(SupportConversationStatus domain) {
        if (domain == null) {
            return null;
        }
        SupportConversationStatusJpaEntity jpa = new SupportConversationStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setLabel(domain.label());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public SupportConversationStatus toDomain(SupportConversationStatusJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return SupportConversationStatus.restore(
                new SupportConversationStatusId(jpa.getId()),
                jpa.getCode(),
                jpa.getLabel(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
