package com.carelink.domain.sessionnote.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.sessionnote.model.aggregate.SessionNote;
import com.carelink.domain.sessionnote.model.valueobject.SessionNoteId;

public interface SessionNoteRepository {
    SessionNote save(SessionNote aggregate);

    Optional<SessionNote> findById(SessionNoteId id);

    List<SessionNote> findAll();

    void delete(SessionNote aggregate);
}
