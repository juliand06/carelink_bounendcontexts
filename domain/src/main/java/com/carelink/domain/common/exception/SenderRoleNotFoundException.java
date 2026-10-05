package com.carelink.domain.common.exception;

import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;

public class SenderRoleNotFoundException extends RuntimeException {
    public SenderRoleNotFoundException(SenderRoleId id) {
        super("SenderRole not found with id: " + id.value());
    }
}
