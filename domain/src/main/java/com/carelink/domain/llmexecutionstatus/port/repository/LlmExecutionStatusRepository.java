package com.carelink.domain.llmexecutionstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.llmexecutionstatus.model.aggregate.LlmExecutionStatus;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;

public interface LlmExecutionStatusRepository {
    LlmExecutionStatus save(LlmExecutionStatus aggregate);

    Optional<LlmExecutionStatus> findById(LlmExecutionStatusId id);

    List<LlmExecutionStatus> findAll();

    void delete(LlmExecutionStatus aggregate);
}
