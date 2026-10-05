package com.carelink.application.llmexecution.usecase;

import com.carelink.application.llmexecution.command.UpdateLlmExecutionCommand;
import com.carelink.application.llmexecution.dto.LlmExecutionResponse;
import com.carelink.domain.llmexecution.port.repository.LlmExecutionRepository;
import com.carelink.application.llmexecution.exception.LlmExecutionNotFoundApplicationException;

public class UpdateLlmExecutionUseCase {
    private final LlmExecutionRepository repository;

    public UpdateLlmExecutionUseCase(LlmExecutionRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionResponse execute(UpdateLlmExecutionCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new LlmExecutionNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.conversationId(),
                command.messageId(),
                command.modelId(),
                command.executionStatusId());
        var saved = repository.save(aggregate);
        return new LlmExecutionResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.messageId().value(),
                saved.modelId().value(),
                saved.executionStatusId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
