package com.carelink.infrastructure.llmvendor.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "llm_vendors", uniqueConstraints = {
        @UniqueConstraint(name = "uk_llm_vendors_brand_name", columnNames = "brand_name")
})
public class LlmVendorJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "brand_name", nullable = false, length = 100)
    private String brandName;

    @Column(name = "legal_name", nullable = true, length = 255)
    private String legalName;

    @Column(name = "website_url", nullable = true, columnDefinition = "text")
    private String websiteUrl;

    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public LlmVendorJpaEntity() { }

    public LlmVendorJpaEntity(
            UUID id,
            String brandName,
            String legalName,
            String websiteUrl,
            Boolean isEnabled,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.brandName = brandName;
        this.legalName = legalName;
        this.websiteUrl = websiteUrl;
        this.isEnabled = isEnabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getBrandName() { return brandName; }
    public void setBrandName(String brandName) { this.brandName = brandName; }
    public String getLegalName() { return legalName; }
    public void setLegalName(String legalName) { this.legalName = legalName; }
    public String getWebsiteUrl() { return websiteUrl; }
    public void setWebsiteUrl(String websiteUrl) { this.websiteUrl = websiteUrl; }
    public Boolean getIsEnabled() { return isEnabled; }
    public void setIsEnabled(Boolean isEnabled) { this.isEnabled = isEnabled; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
