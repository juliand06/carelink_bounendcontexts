package com.carelink.domain.conversationaiconfig.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.conversationaiconfig.model.aggregate.ConversationAiConfig;
import com.carelink.domain.conversationaiconfig.model.valueobject.ConversationAiConfigId;

public interface ConversationAiConfigRepository {
    ConversationAiConfig save(ConversationAiConfig aggregate);

    Optional<ConversationAiConfig> findById(ConversationAiConfigId id);

    List<ConversationAiConfig> findAll();

    void delete(ConversationAiConfig aggregate);
}
