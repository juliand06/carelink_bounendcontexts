package com.carelink.infrastructure.llmmodel.adapters.out.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "llm_models", uniqueConstraints = {
        @UniqueConstraint(name = "uk_llm_models_api_identifier", columnNames = "api_identifier")
})
public class LlmModelJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "llm_vendor_id", nullable = false)
    private UUID llmVendorId;

    @Column(name = "display_name", nullable = false, length = 100)
    private String displayName;

    @Column(name = "api_identifier", nullable = false, length = 120)
    private String apiIdentifier;

    @Column(name = "price_per_input_token", nullable = false, precision = 12, scale = 8)
    private BigDecimal pricePerInputToken;

    @Column(name = "price_per_output_token", nullable = false, precision = 12, scale = 8)
    private BigDecimal pricePerOutputToken;

    @Column(name = "max_output_tokens", nullable = false)
    private Integer maxOutputTokens;

    @Column(name = "context_length", nullable = false)
    private Integer contextLength;

    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public LlmModelJpaEntity() { }

    public LlmModelJpaEntity(
            UUID id,
            UUID llmVendorId,
            String displayName,
            String apiIdentifier,
            BigDecimal pricePerInputToken,
            BigDecimal pricePerOutputToken,
            Integer maxOutputTokens,
            Integer contextLength,
            Boolean isEnabled,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.llmVendorId = llmVendorId;
        this.displayName = displayName;
        this.apiIdentifier = apiIdentifier;
        this.pricePerInputToken = pricePerInputToken;
        this.pricePerOutputToken = pricePerOutputToken;
        this.maxOutputTokens = maxOutputTokens;
        this.contextLength = contextLength;
        this.isEnabled = isEnabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getLlmVendorId() { return llmVendorId; }
    public void setLlmVendorId(UUID llmVendorId) { this.llmVendorId = llmVendorId; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public String getApiIdentifier() { return apiIdentifier; }
    public void setApiIdentifier(String apiIdentifier) { this.apiIdentifier = apiIdentifier; }
    public BigDecimal getPricePerInputToken() { return pricePerInputToken; }
    public void setPricePerInputToken(BigDecimal pricePerInputToken) { this.pricePerInputToken = pricePerInputToken; }
    public BigDecimal getPricePerOutputToken() { return pricePerOutputToken; }
    public void setPricePerOutputToken(BigDecimal pricePerOutputToken) { this.pricePerOutputToken = pricePerOutputToken; }
    public Integer getMaxOutputTokens() { return maxOutputTokens; }
    public void setMaxOutputTokens(Integer maxOutputTokens) { this.maxOutputTokens = maxOutputTokens; }
    public Integer getContextLength() { return contextLength; }
    public void setContextLength(Integer contextLength) { this.contextLength = contextLength; }
    public Boolean getIsEnabled() { return isEnabled; }
    public void setIsEnabled(Boolean isEnabled) { this.isEnabled = isEnabled; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
