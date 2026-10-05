package com.carelink.infrastructure.supportconversation.adapters.out.persistence.mappers;

import com.carelink.domain.supportconversation.model.aggregate.SupportConversation;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;
import com.carelink.infrastructure.supportconversation.adapters.out.persistence.entity.SupportConversationJpaEntity;

public class SupportConversationPersistenceMapper {
    public SupportConversationJpaEntity toJpa(SupportConversation domain) {
        if (domain == null) {
            return null;
        }
        SupportConversationJpaEntity jpa = new SupportConversationJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationStatusId(domain.conversationStatusId().value());
        jpa.setUrgencyLevelId(domain.urgencyLevelId().value());
        jpa.setLastInteractionAt(domain.lastInteractionAt());
        jpa.setIsClosed(domain.isClosed());
        jpa.setClosedAt(domain.closedAt());
        jpa.setClosedBy(domain.closedBy());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public SupportConversation toDomain(SupportConversationJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return SupportConversation.restore(
                new SupportConversationId(jpa.getId()),
                new SupportConversationStatusId(jpa.getConversationStatusId()),
                new UrgencyLevelId(jpa.getUrgencyLevelId()),
                jpa.getLastInteractionAt(),
                jpa.getIsClosed(),
                jpa.getClosedAt(),
                jpa.getClosedBy(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
