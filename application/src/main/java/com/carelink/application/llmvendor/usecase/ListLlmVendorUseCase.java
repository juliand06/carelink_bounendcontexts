package com.carelink.application.llmvendor.usecase;

import java.util.List;

import com.carelink.application.llmvendor.dto.LlmVendorResponse;
import com.carelink.domain.llmvendor.port.repository.LlmVendorRepository;

public class ListLlmVendorUseCase {
    private final LlmVendorRepository repository;

    public ListLlmVendorUseCase(LlmVendorRepository repository) {
        this.repository = repository;
    }

    public List<LlmVendorResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new LlmVendorResponse(
                        aggregate.id().value(),
                        aggregate.brandName(),
                        aggregate.legalName(),
                        aggregate.websiteUrl(),
                        aggregate.isEnabled(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
