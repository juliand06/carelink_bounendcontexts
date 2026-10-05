package com.carelink.domain.llmexecutionerror.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.llmexecutionerror.model.aggregate.LlmExecutionError;
import com.carelink.domain.llmexecutionerror.model.valueobject.LlmExecutionErrorId;

public interface LlmExecutionErrorRepository {
    LlmExecutionError save(LlmExecutionError aggregate);

    Optional<LlmExecutionError> findById(LlmExecutionErrorId id);

    List<LlmExecutionError> findAll();

    void delete(LlmExecutionError aggregate);
}
