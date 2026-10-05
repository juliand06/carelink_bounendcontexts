package com.carelink.domain.llmvendor.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.llmvendor.model.aggregate.LlmVendor;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;

public interface LlmVendorRepository {
    LlmVendor save(LlmVendor aggregate);

    Optional<LlmVendor> findById(LlmVendorId id);

    List<LlmVendor> findAll();

    void delete(LlmVendor aggregate);
}
