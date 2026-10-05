package com.carelink.application.llmmodel.usecase;

import com.carelink.application.llmmodel.command.RegisterLlmModelCommand;
import com.carelink.application.llmmodel.dto.LlmModelResponse;
import com.carelink.domain.llmmodel.port.repository.LlmModelRepository;
import com.carelink.domain.llmmodel.model.aggregate.LlmModel;

public class RegisterLlmModelUseCase {
    private final LlmModelRepository repository;

    public RegisterLlmModelUseCase(LlmModelRepository repository) {
        this.repository = repository;
    }

    public LlmModelResponse execute(RegisterLlmModelCommand command) {
        LlmModel aggregate = LlmModel.register(
                command.llmVendorId(),
                command.displayName(),
                command.apiIdentifier(),
                command.pricePerInputToken(),
                command.pricePerOutputToken(),
                command.maxOutputTokens(),
                command.contextLength(),
                command.isEnabled());
        LlmModel saved = repository.save(aggregate);
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
