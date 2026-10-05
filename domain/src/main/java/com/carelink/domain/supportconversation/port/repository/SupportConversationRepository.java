package com.carelink.domain.supportconversation.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.supportconversation.model.aggregate.SupportConversation;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;

public interface SupportConversationRepository {
    SupportConversation save(SupportConversation aggregate);

    Optional<SupportConversation> findById(SupportConversationId id);

    List<SupportConversation> findAll();

    void delete(SupportConversation aggregate);
}
