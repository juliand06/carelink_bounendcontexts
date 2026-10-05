package com.carelink.application.llmvendor.usecase;

import java.time.LocalDateTime;

import com.carelink.application.llmvendor.exception.LlmVendorNotFoundApplicationException;
import com.carelink.domain.llmvendor.event.LlmVendorDeletedEvent;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;
import com.carelink.domain.llmvendor.port.repository.LlmVendorRepository;

public class DeleteLlmVendorUseCase {
    private final LlmVendorRepository repository;

    public DeleteLlmVendorUseCase(LlmVendorRepository repository) {
        this.repository = repository;
    }

    public LlmVendorDeletedEvent execute(LlmVendorId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new LlmVendorNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new LlmVendorDeletedEvent(id, LocalDateTime.now());
    }
}
