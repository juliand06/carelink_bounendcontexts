package com.carelink.application.llmexecutionmetric.usecase;

import com.carelink.application.llmexecutionmetric.command.UpdateLlmExecutionMetricCommand;
import com.carelink.application.llmexecutionmetric.dto.LlmExecutionMetricResponse;
import com.carelink.domain.llmexecutionmetric.port.repository.LlmExecutionMetricRepository;
import com.carelink.application.llmexecutionmetric.exception.LlmExecutionMetricNotFoundApplicationException;

public class UpdateLlmExecutionMetricUseCase {
    private final LlmExecutionMetricRepository repository;

    public UpdateLlmExecutionMetricUseCase(LlmExecutionMetricRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionMetricResponse execute(UpdateLlmExecutionMetricCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new LlmExecutionMetricNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.executionId(),
                command.inputTokens(),
                command.outputTokens(),
                command.totalTokens(),
                command.totalCost());
        var saved = repository.save(aggregate);
        return new LlmExecutionMetricResponse(
                saved.id().value(),
                saved.executionId().value(),
                saved.inputTokens(),
                saved.outputTokens(),
                saved.totalTokens(),
                saved.totalCost(),
                saved.createdAt());
    }
}
