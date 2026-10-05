package com.carelink.domain.common.exception;

import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;

public class IdDocumentTypeNotFoundException extends RuntimeException {
    public IdDocumentTypeNotFoundException(IdDocumentTypeId id) {
        super("IdDocumentType not found with id: " + id.value());
    }
}
