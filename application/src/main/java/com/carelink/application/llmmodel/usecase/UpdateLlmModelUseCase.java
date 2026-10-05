package com.carelink.application.llmmodel.usecase;

import com.carelink.application.llmmodel.command.UpdateLlmModelCommand;
import com.carelink.application.llmmodel.dto.LlmModelResponse;
import com.carelink.domain.llmmodel.port.repository.LlmModelRepository;
import com.carelink.application.llmmodel.exception.LlmModelNotFoundApplicationException;

public class UpdateLlmModelUseCase {
    private final LlmModelRepository repository;

    public UpdateLlmModelUseCase(LlmModelRepository repository) {
        this.repository = repository;
    }

    public LlmModelResponse execute(UpdateLlmModelCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new LlmModelNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.llmVendorId(),
                command.displayName(),
                command.apiIdentifier(),
                command.pricePerInputToken(),
                command.pricePerOutputToken(),
                command.maxOutputTokens(),
                command.contextLength(),
                command.isEnabled());
        var saved = repository.save(aggregate);
        return new LlmModelResponse(
                saved.id().value(),
                saved.llmVendorId().value(),
                saved.displayName(),
                saved.apiIdentifier(),
                saved.pricePerInputToken(),
                saved.pricePerOutputToken(),
                saved.maxOutputTokens(),
                saved.contextLength(),
                saved.isEnabled(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
