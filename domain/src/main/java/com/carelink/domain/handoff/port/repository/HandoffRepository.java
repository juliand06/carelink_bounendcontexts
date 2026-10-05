package com.carelink.domain.handoff.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.handoff.model.aggregate.Handoff;
import com.carelink.domain.handoff.model.valueobject.HandoffId;

public interface HandoffRepository {
    Handoff save(Handoff aggregate);

    Optional<Handoff> findById(HandoffId id);

    List<Handoff> findAll();

    void delete(Handoff aggregate);
}
