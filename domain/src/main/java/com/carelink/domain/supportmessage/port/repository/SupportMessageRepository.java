package com.carelink.domain.supportmessage.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.supportmessage.model.aggregate.SupportMessage;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;

public interface SupportMessageRepository {
    SupportMessage save(SupportMessage aggregate);

    Optional<SupportMessage> findById(SupportMessageId id);

    List<SupportMessage> findAll();

    void delete(SupportMessage aggregate);
}
