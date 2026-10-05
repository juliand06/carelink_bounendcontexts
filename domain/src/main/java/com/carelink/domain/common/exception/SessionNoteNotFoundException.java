package com.carelink.domain.common.exception;

import com.carelink.domain.sessionnote.model.valueobject.SessionNoteId;

public class SessionNoteNotFoundException extends RuntimeException {
    public SessionNoteNotFoundException(SessionNoteId id) {
        super("SessionNote not found with id: " + id.value());
    }
}
