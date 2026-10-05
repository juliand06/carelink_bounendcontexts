package com.carelink.domain.handoffassignment.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.handoffassignment.model.aggregate.HandoffAssignment;
import com.carelink.domain.handoffassignment.model.valueobject.HandoffAssignmentId;

public interface HandoffAssignmentRepository {
    HandoffAssignment save(HandoffAssignment aggregate);

    Optional<HandoffAssignment> findById(HandoffAssignmentId id);

    List<HandoffAssignment> findAll();

    void delete(HandoffAssignment aggregate);
}
