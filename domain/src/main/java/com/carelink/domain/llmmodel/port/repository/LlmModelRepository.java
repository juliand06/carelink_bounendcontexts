package com.carelink.domain.llmmodel.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.llmmodel.model.aggregate.LlmModel;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;

public interface LlmModelRepository {
    LlmModel save(LlmModel aggregate);

    Optional<LlmModel> findById(LlmModelId id);

    List<LlmModel> findAll();

    void delete(LlmModel aggregate);
}
