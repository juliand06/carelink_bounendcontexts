package com.carelink.application.llmvendor.usecase;

import com.carelink.application.llmvendor.dto.LlmVendorResponse;
import com.carelink.domain.llmvendor.port.repository.LlmVendorRepository;
import com.carelink.application.llmvendor.exception.LlmVendorNotFoundApplicationException;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;

public class GetLlmVendorByIdUseCase {
    private final LlmVendorRepository repository;

    public GetLlmVendorByIdUseCase(LlmVendorRepository repository) {
        this.repository = repository;
    }

    public LlmVendorResponse execute(LlmVendorId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new LlmVendorNotFoundApplicationException(id.value().toString()));
        return new LlmVendorResponse(
                aggregate.id().value(),
                aggregate.brandName(),
                aggregate.legalName(),
                aggregate.websiteUrl(),
                aggregate.isEnabled(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
