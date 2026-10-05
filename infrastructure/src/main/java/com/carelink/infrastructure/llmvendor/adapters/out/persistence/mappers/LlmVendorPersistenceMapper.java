package com.carelink.infrastructure.llmvendor.adapters.out.persistence.mappers;

import com.carelink.domain.llmvendor.model.aggregate.LlmVendor;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;
import com.carelink.infrastructure.llmvendor.adapters.out.persistence.entity.LlmVendorJpaEntity;

public class LlmVendorPersistenceMapper {
    public LlmVendorJpaEntity toJpa(LlmVendor domain) {
        if (domain == null) {
            return null;
        }
        LlmVendorJpaEntity jpa = new LlmVendorJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setBrandName(domain.brandName());
        jpa.setLegalName(domain.legalName());
        jpa.setWebsiteUrl(domain.websiteUrl());
        jpa.setIsEnabled(domain.isEnabled());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public LlmVendor toDomain(LlmVendorJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return LlmVendor.restore(
                new LlmVendorId(jpa.getId()),
                jpa.getBrandName(),
                jpa.getLegalName(),
                jpa.getWebsiteUrl(),
                jpa.getIsEnabled(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
