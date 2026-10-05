package com.carelink.domain.llmmodel.model.aggregate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.llmmodel.event.LlmModelRegisteredEvent;
import com.carelink.domain.llmmodel.event.LlmModelUpdatedEvent;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;

public class LlmModel extends AggregateRoot {
    private final LlmModelId id;
    private LlmVendorId llmVendorId;
    private String displayName;
    private String apiIdentifier;
    private BigDecimal pricePerInputToken;
    private BigDecimal pricePerOutputToken;
    private Integer maxOutputTokens;
    private Integer contextLength;
    private Boolean isEnabled;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private LlmModel(
            LlmModelId id,
            LlmVendorId llmVendorId,
            String displayName,
            String apiIdentifier,
            BigDecimal pricePerInputToken,
            BigDecimal pricePerOutputToken,
            Integer maxOutputTokens,
            Integer contextLength,
            Boolean isEnabled,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.llmVendorId = Objects.requireNonNull(llmVendorId, "llmVendorId must not be null");
        this.displayName = Objects.requireNonNull(displayName, "displayName must not be null");
        this.apiIdentifier = Objects.requireNonNull(apiIdentifier, "apiIdentifier must not be null");
        this.pricePerInputToken = Objects.requireNonNull(pricePerInputToken, "pricePerInputToken must not be null");
        this.pricePerOutputToken = Objects.requireNonNull(pricePerOutputToken, "pricePerOutputToken must not be null");
        this.maxOutputTokens = Objects.requireNonNull(maxOutputTokens, "maxOutputTokens must not be null");
        this.contextLength = Objects.requireNonNull(contextLength, "contextLength must not be null");
        this.isEnabled = Objects.requireNonNull(isEnabled, "isEnabled must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static LlmModel register(
            LlmVendorId llmVendorId,
            String displayName,
            String apiIdentifier,
            BigDecimal pricePerInputToken,
            BigDecimal pricePerOutputToken,
            Integer maxOutputTokens,
            Integer contextLength,
            Boolean isEnabled) {
        LlmModelId id = LlmModelId.generate();
        LocalDateTime now = LocalDateTime.now();
        LlmModel aggregate = new LlmModel(
                id,
                llmVendorId,
                displayName,
                apiIdentifier,
                pricePerInputToken,
                pricePerOutputToken,
                maxOutputTokens,
                contextLength,
                isEnabled,
                now,
                now);
        aggregate.recordEvent(new LlmModelRegisteredEvent(id, now));
        return aggregate;
    }

    public static LlmModel restore(
            LlmModelId id,
            LlmVendorId llmVendorId,
            String displayName,
            String apiIdentifier,
            BigDecimal pricePerInputToken,
            BigDecimal pricePerOutputToken,
            Integer maxOutputTokens,
            Integer contextLength,
            Boolean isEnabled,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new LlmModel(
                id,
                llmVendorId,
                displayName,
                apiIdentifier,
                pricePerInputToken,
                pricePerOutputToken,
                maxOutputTokens,
                contextLength,
                isEnabled,
                createdAt,
                updatedAt);
    }

    public void update(
            LlmVendorId llmVendorId,
            String displayName,
            String apiIdentifier,
            BigDecimal pricePerInputToken,
            BigDecimal pricePerOutputToken,
            Integer maxOutputTokens,
            Integer contextLength,
            Boolean isEnabled) {
        this.llmVendorId = Objects.requireNonNull(llmVendorId, "llmVendorId must not be null");
        this.displayName = Objects.requireNonNull(displayName, "displayName must not be null");
        this.apiIdentifier = Objects.requireNonNull(apiIdentifier, "apiIdentifier must not be null");
        this.pricePerInputToken = Objects.requireNonNull(pricePerInputToken, "pricePerInputToken must not be null");
        this.pricePerOutputToken = Objects.requireNonNull(pricePerOutputToken, "pricePerOutputToken must not be null");
        this.maxOutputTokens = Objects.requireNonNull(maxOutputTokens, "maxOutputTokens must not be null");
        this.contextLength = Objects.requireNonNull(contextLength, "contextLength must not be null");
        this.isEnabled = Objects.requireNonNull(isEnabled, "isEnabled must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new LlmModelUpdatedEvent(
                this.id,
                this.llmVendorId,
                this.displayName,
                this.apiIdentifier,
                this.pricePerInputToken,
                this.pricePerOutputToken,
                this.maxOutputTokens,
                this.contextLength,
                this.isEnabled,
                now));
    }

    public LlmModelId id() {
        return id;
    }

    public LlmVendorId llmVendorId() {
        return llmVendorId;
    }

    public String displayName() {
        return displayName;
    }

    public String apiIdentifier() {
        return apiIdentifier;
    }

    public BigDecimal pricePerInputToken() {
        return pricePerInputToken;
    }

    public BigDecimal pricePerOutputToken() {
        return pricePerOutputToken;
    }

    public Integer maxOutputTokens() {
        return maxOutputTokens;
    }

    public Integer contextLength() {
        return contextLength;
    }

    public Boolean isEnabled() {
        return isEnabled;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
