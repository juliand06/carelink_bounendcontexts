package com.carelink.domain.llmexecution.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.llmexecution.model.aggregate.LlmExecution;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;

public interface LlmExecutionRepository {
    LlmExecution save(LlmExecution aggregate);

    Optional<LlmExecution> findById(LlmExecutionId id);

    List<LlmExecution> findAll();

    void delete(LlmExecution aggregate);
}
