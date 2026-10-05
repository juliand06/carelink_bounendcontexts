package com.carelink.domain.messageformat.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.messageformat.model.aggregate.MessageFormat;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;

public interface MessageFormatRepository {
    MessageFormat save(MessageFormat aggregate);

    Optional<MessageFormat> findById(MessageFormatId id);

    List<MessageFormat> findAll();

    void delete(MessageFormat aggregate);
}
