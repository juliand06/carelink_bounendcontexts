package com.carelink.application.llmmodel.usecase;

import com.carelink.application.llmmodel.dto.LlmModelResponse;
import com.carelink.domain.llmmodel.port.repository.LlmModelRepository;
import com.carelink.application.llmmodel.exception.LlmModelNotFoundApplicationException;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;

public class GetLlmModelByIdUseCase {
    private final LlmModelRepository repository;

    public GetLlmModelByIdUseCase(LlmModelRepository repository) {
        this.repository = repository;
    }

    public LlmModelResponse execute(LlmModelId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new LlmModelNotFoundApplicationException(id.value().toString()));
        return new LlmModelResponse(
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
                aggregate.updatedAt());
    }
}
