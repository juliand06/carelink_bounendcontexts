package com.carelink.application.llmexecutionstatus.usecase;

import com.carelink.application.llmexecutionstatus.command.UpdateLlmExecutionStatusCommand;
import com.carelink.application.llmexecutionstatus.dto.LlmExecutionStatusResponse;
import com.carelink.domain.llmexecutionstatus.port.repository.LlmExecutionStatusRepository;
import com.carelink.application.llmexecutionstatus.exception.LlmExecutionStatusNotFoundApplicationException;

public class UpdateLlmExecutionStatusUseCase {
    private final LlmExecutionStatusRepository repository;

    public UpdateLlmExecutionStatusUseCase(LlmExecutionStatusRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionStatusResponse execute(UpdateLlmExecutionStatusCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new LlmExecutionStatusNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.label());
        var saved = repository.save(aggregate);
        return new LlmExecutionStatusResponse(
                saved.id().value(),
                saved.label(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
