package com.carelink.infrastructure.llmmodel.adapters.out.persistence.mappers;

import com.carelink.domain.llmmodel.model.aggregate.LlmModel;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;
import com.carelink.infrastructure.llmmodel.adapters.out.persistence.entity.LlmModelJpaEntity;

public class LlmModelPersistenceMapper {
    public LlmModelJpaEntity toJpa(LlmModel domain) {
        if (domain == null) {
            return null;
        }
        LlmModelJpaEntity jpa = new LlmModelJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setLlmVendorId(domain.llmVendorId().value());
        jpa.setDisplayName(domain.displayName());
        jpa.setApiIdentifier(domain.apiIdentifier());
        jpa.setPricePerInputToken(domain.pricePerInputToken());
        jpa.setPricePerOutputToken(domain.pricePerOutputToken());
        jpa.setMaxOutputTokens(domain.maxOutputTokens());
        jpa.setContextLength(domain.contextLength());
        jpa.setIsEnabled(domain.isEnabled());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public LlmModel toDomain(LlmModelJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return LlmModel.restore(
                new LlmModelId(jpa.getId()),
                new LlmVendorId(jpa.getLlmVendorId()),
                jpa.getDisplayName(),
                jpa.getApiIdentifier(),
                jpa.getPricePerInputToken(),
                jpa.getPricePerOutputToken(),
                jpa.getMaxOutputTokens(),
                jpa.getContextLength(),
                jpa.getIsEnabled(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
