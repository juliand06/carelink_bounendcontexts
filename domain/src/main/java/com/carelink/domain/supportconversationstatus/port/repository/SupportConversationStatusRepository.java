package com.carelink.domain.supportconversationstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.supportconversationstatus.model.aggregate.SupportConversationStatus;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;

public interface SupportConversationStatusRepository {
    SupportConversationStatus save(SupportConversationStatus aggregate);

    Optional<SupportConversationStatus> findById(SupportConversationStatusId id);

    List<SupportConversationStatus> findAll();

    void delete(SupportConversationStatus aggregate);
}
