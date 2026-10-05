package com.carelink.infrastructure.conversationaiconfig.adapters.out.persistence.mappers;

import com.carelink.domain.conversationaiconfig.model.aggregate.ConversationAiConfig;
import com.carelink.domain.conversationaiconfig.model.valueobject.ConversationAiConfigId;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.infrastructure.conversationaiconfig.adapters.out.persistence.entity.ConversationAiConfigJpaEntity;

public class ConversationAiConfigPersistenceMapper {
    public ConversationAiConfigJpaEntity toJpa(ConversationAiConfig domain) {
        if (domain == null) {
            return null;
        }
        ConversationAiConfigJpaEntity jpa = new ConversationAiConfigJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId().value());
        jpa.setAiEnabled(domain.aiEnabled());
        jpa.setPreferredModelId(domain.preferredModelId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public ConversationAiConfig toDomain(ConversationAiConfigJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return ConversationAiConfig.restore(
                new ConversationAiConfigId(jpa.getId()),
                new SupportConversationId(jpa.getConversationId()),
                jpa.getAiEnabled(),
                new LlmModelId(jpa.getPreferredModelId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
