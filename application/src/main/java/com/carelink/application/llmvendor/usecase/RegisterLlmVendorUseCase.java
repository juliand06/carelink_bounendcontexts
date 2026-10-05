package com.carelink.application.llmvendor.usecase;

import com.carelink.application.llmvendor.command.RegisterLlmVendorCommand;
import com.carelink.application.llmvendor.dto.LlmVendorResponse;
import com.carelink.domain.llmvendor.port.repository.LlmVendorRepository;
import com.carelink.domain.llmvendor.model.aggregate.LlmVendor;

public class RegisterLlmVendorUseCase {
    private final LlmVendorRepository repository;

    public RegisterLlmVendorUseCase(LlmVendorRepository repository) {
        this.repository = repository;
    }

    public LlmVendorResponse execute(RegisterLlmVendorCommand command) {
        LlmVendor aggregate = LlmVendor.register(
                command.brandName(),
                command.legalName(),
                command.websiteUrl(),
                command.isEnabled());
        LlmVendor saved = repository.save(aggregate);
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
