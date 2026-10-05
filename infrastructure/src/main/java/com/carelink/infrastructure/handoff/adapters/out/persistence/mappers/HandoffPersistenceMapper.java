package com.carelink.infrastructure.handoff.adapters.out.persistence.mappers;

import com.carelink.domain.handoff.model.aggregate.Handoff;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.infrastructure.handoff.adapters.out.persistence.entity.HandoffJpaEntity;

public class HandoffPersistenceMapper {
    public HandoffJpaEntity toJpa(Handoff domain) {
        if (domain == null) {
            return null;
        }
        HandoffJpaEntity jpa = new HandoffJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId().value());
        jpa.setHandoffStatusId(domain.handoffStatusId().value());
        jpa.setOriginatedFromAi(domain.originatedFromAi());
        jpa.setHandoffReason(domain.handoffReason());
        jpa.setCreatedAt(domain.createdAt());
        return jpa;
    }

    public Handoff toDomain(HandoffJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return Handoff.restore(
                new HandoffId(jpa.getId()),
                new SupportConversationId(jpa.getConversationId()),
                new HandoffStatusId(jpa.getHandoffStatusId()),
                jpa.getOriginatedFromAi(),
                jpa.getHandoffReason(),
                jpa.getCreatedAt());
    }
}
