package com.carelink.infrastructure.messageformat.adapters.out.persistence.mappers;

import com.carelink.domain.messageformat.model.aggregate.MessageFormat;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;
import com.carelink.infrastructure.messageformat.adapters.out.persistence.entity.MessageFormatJpaEntity;

public class MessageFormatPersistenceMapper {
    public MessageFormatJpaEntity toJpa(MessageFormat domain) {
        if (domain == null) {
            return null;
        }
        MessageFormatJpaEntity jpa = new MessageFormatJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setLabel(domain.label());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public MessageFormat toDomain(MessageFormatJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return MessageFormat.restore(
                new MessageFormatId(jpa.getId()),
                jpa.getLabel(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
