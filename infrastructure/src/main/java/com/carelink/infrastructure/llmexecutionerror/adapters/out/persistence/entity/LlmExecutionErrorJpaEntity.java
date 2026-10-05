package com.carelink.infrastructure.llmexecutionerror.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "llm_execution_errors")
public class LlmExecutionErrorJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "execution_id", nullable = false)
    private UUID executionId;

    @Column(name = "error_detail", nullable = false, columnDefinition = "text")
    private String errorDetail;

    @Column(name = "error_code", nullable = true, length = 80)
    private String errorCode;

    @Column(name = "vendor_error_ref", nullable = true, length = 120)
    private String vendorErrorRef;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public LlmExecutionErrorJpaEntity() { }

    public LlmExecutionErrorJpaEntity(
            UUID id,
            UUID executionId,
            String errorDetail,
            String errorCode,
            String vendorErrorRef,
            LocalDateTime createdAt) {
        this.id = id;
        this.executionId = executionId;
        this.errorDetail = errorDetail;
        this.errorCode = errorCode;
        this.vendorErrorRef = vendorErrorRef;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getExecutionId() { return executionId; }
    public void setExecutionId(UUID executionId) { this.executionId = executionId; }
    public String getErrorDetail() { return errorDetail; }
    public void setErrorDetail(String errorDetail) { this.errorDetail = errorDetail; }
    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
    public String getVendorErrorRef() { return vendorErrorRef; }
    public void setVendorErrorRef(String vendorErrorRef) { this.vendorErrorRef = vendorErrorRef; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
