package com.carelink.infrastructure.llmexecution.adapters.out.persistence.mappers;

import com.carelink.domain.llmexecution.model.aggregate.LlmExecution;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;
import com.carelink.infrastructure.llmexecution.adapters.out.persistence.entity.LlmExecutionJpaEntity;

public class LlmExecutionPersistenceMapper {
    public LlmExecutionJpaEntity toJpa(LlmExecution domain) {
        if (domain == null) {
            return null;
        }
        LlmExecutionJpaEntity jpa = new LlmExecutionJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId().value());
        jpa.setMessageId(domain.messageId().value());
        jpa.setModelId(domain.modelId().value());
        jpa.setExecutionStatusId(domain.executionStatusId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public LlmExecution toDomain(LlmExecutionJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return LlmExecution.restore(
                new LlmExecutionId(jpa.getId()),
                new SupportConversationId(jpa.getConversationId()),
                new SupportMessageId(jpa.getMessageId()),
                new LlmModelId(jpa.getModelId()),
                new LlmExecutionStatusId(jpa.getExecutionStatusId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
