package com.carelink.domain.llmvendor.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.llmvendor.event.LlmVendorRegisteredEvent;
import com.carelink.domain.llmvendor.event.LlmVendorUpdatedEvent;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;

public class LlmVendor extends AggregateRoot {
    private final LlmVendorId id;
    private String brandName;
    private String legalName;
    private String websiteUrl;
    private Boolean isEnabled;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private LlmVendor(
            LlmVendorId id,
            String brandName,
            String legalName,
            String websiteUrl,
            Boolean isEnabled,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.brandName = Objects.requireNonNull(brandName, "brandName must not be null");
        this.legalName = legalName;
        this.websiteUrl = websiteUrl;
        this.isEnabled = Objects.requireNonNull(isEnabled, "isEnabled must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static LlmVendor register(
            String brandName,
            String legalName,
            String websiteUrl,
            Boolean isEnabled) {
        LlmVendorId id = LlmVendorId.generate();
        LocalDateTime now = LocalDateTime.now();
        LlmVendor aggregate = new LlmVendor(
                id,
                brandName,
                legalName,
                websiteUrl,
                isEnabled,
                now,
                now);
        aggregate.recordEvent(new LlmVendorRegisteredEvent(id, now));
        return aggregate;
    }

    public static LlmVendor restore(
            LlmVendorId id,
            String brandName,
            String legalName,
            String websiteUrl,
            Boolean isEnabled,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new LlmVendor(
                id,
                brandName,
                legalName,
                websiteUrl,
                isEnabled,
                createdAt,
                updatedAt);
    }

    public void update(
            String brandName,
            String legalName,
            String websiteUrl,
            Boolean isEnabled) {
        this.brandName = Objects.requireNonNull(brandName, "brandName must not be null");
        this.legalName = legalName;
        this.websiteUrl = websiteUrl;
        this.isEnabled = Objects.requireNonNull(isEnabled, "isEnabled must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new LlmVendorUpdatedEvent(
                this.id,
                this.brandName,
                this.legalName,
                this.websiteUrl,
                this.isEnabled,
                now));
    }

    public LlmVendorId id() {
        return id;
    }

    public String brandName() {
        return brandName;
    }

    public String legalName() {
        return legalName;
    }

    public String websiteUrl() {
        return websiteUrl;
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
