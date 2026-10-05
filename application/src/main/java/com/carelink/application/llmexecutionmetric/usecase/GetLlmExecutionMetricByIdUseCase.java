package com.carelink.application.llmexecutionmetric.usecase;

import com.carelink.application.llmexecutionmetric.dto.LlmExecutionMetricResponse;
import com.carelink.domain.llmexecutionmetric.port.repository.LlmExecutionMetricRepository;
import com.carelink.application.llmexecutionmetric.exception.LlmExecutionMetricNotFoundApplicationException;
import com.carelink.domain.llmexecutionmetric.model.valueobject.LlmExecutionMetricId;

public class GetLlmExecutionMetricByIdUseCase {
    private final LlmExecutionMetricRepository repository;

    public GetLlmExecutionMetricByIdUseCase(LlmExecutionMetricRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionMetricResponse execute(LlmExecutionMetricId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new LlmExecutionMetricNotFoundApplicationException(id.value().toString()));
        return new LlmExecutionMetricResponse(
                aggregate.id().value(),
                aggregate.executionId().value(),
                aggregate.inputTokens(),
                aggregate.outputTokens(),
                aggregate.totalTokens(),
                aggregate.totalCost(),
                aggregate.createdAt());
    }
}
