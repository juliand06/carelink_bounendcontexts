package com.carelink.domain.classificationsystem.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.classificationsystem.model.aggregate.ClassificationSystem;
import com.carelink.domain.classificationsystem.model.valueobject.ClassificationSystemId;

public interface ClassificationSystemRepository {
    ClassificationSystem save(ClassificationSystem aggregate);

    Optional<ClassificationSystem> findById(ClassificationSystemId id);

    List<ClassificationSystem> findAll();

    void delete(ClassificationSystem aggregate);
}
