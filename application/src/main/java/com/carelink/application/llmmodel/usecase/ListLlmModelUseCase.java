package com.carelink.application.llmmodel.usecase;

import java.util.List;

import com.carelink.application.llmmodel.dto.LlmModelResponse;
import com.carelink.domain.llmmodel.port.repository.LlmModelRepository;

public class ListLlmModelUseCase {
    private final LlmModelRepository repository;

    public ListLlmModelUseCase(LlmModelRepository repository) {
        this.repository = repository;
    }

    public List<LlmModelResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new LlmModelResponse(
                        aggregate.id().value(),
                        aggregate.llmVendorId().value(),
                        aggregate.displayName(),
                        aggregate.apiIdentifier(),
                        aggregate.pricePerInputToken(),
                        aggregate.pricePerOutputToken(),
                        aggregate.maxOutputTokens(),
                        aggregate.contextLength(),
                        aggregate.isEnabled(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
