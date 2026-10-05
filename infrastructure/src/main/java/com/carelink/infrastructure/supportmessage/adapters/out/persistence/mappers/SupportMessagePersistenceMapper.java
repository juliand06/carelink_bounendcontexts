package com.carelink.infrastructure.supportmessage.adapters.out.persistence.mappers;

import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportmessage.model.aggregate.SupportMessage;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;
import com.carelink.infrastructure.supportmessage.adapters.out.persistence.entity.SupportMessageJpaEntity;

public class SupportMessagePersistenceMapper {
    public SupportMessageJpaEntity toJpa(SupportMessage domain) {
        if (domain == null) {
            return null;
        }
        SupportMessageJpaEntity jpa = new SupportMessageJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId().value());
        jpa.setMessageFormatId(domain.messageFormatId().value());
        jpa.setMemberId(domain.memberId().value());
        jpa.setPayload(domain.payload());
        jpa.setExtraData(domain.extraData());
        jpa.setCreatedAt(domain.createdAt());
        return jpa;
    }

    public SupportMessage toDomain(SupportMessageJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return SupportMessage.restore(
                new SupportMessageId(jpa.getId()),
                new SupportConversationId(jpa.getConversationId()),
                new MessageFormatId(jpa.getMessageFormatId()),
                new ConversationMemberId(jpa.getMemberId()),
                jpa.getPayload(),
                jpa.getExtraData(),
                jpa.getCreatedAt());
    }
}
