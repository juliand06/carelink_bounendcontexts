package com.carelink.application.llmvendor.usecase;

import com.carelink.application.llmvendor.command.UpdateLlmVendorCommand;
import com.carelink.application.llmvendor.dto.LlmVendorResponse;
import com.carelink.domain.llmvendor.port.repository.LlmVendorRepository;
import com.carelink.application.llmvendor.exception.LlmVendorNotFoundApplicationException;

public class UpdateLlmVendorUseCase {
    private final LlmVendorRepository repository;

    public UpdateLlmVendorUseCase(LlmVendorRepository repository) {
        this.repository = repository;
    }

    public LlmVendorResponse execute(UpdateLlmVendorCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new LlmVendorNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.brandName(),
                command.legalName(),
                command.websiteUrl(),
                command.isEnabled());
        var saved = repository.save(aggregate);
        return new LlmVendorResponse(
                saved.id().value(),
                saved.brandName(),
                saved.legalName(),
                saved.websiteUrl(),
                saved.isEnabled(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
