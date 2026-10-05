package com.carelink.infrastructure.conversationmember.adapters.out.persistence.mappers;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.conversationmember.model.aggregate.ConversationMember;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.infrastructure.conversationmember.adapters.out.persistence.entity.ConversationMemberJpaEntity;

public class ConversationMemberPersistenceMapper {
    public ConversationMemberJpaEntity toJpa(ConversationMember domain) {
        if (domain == null) {
            return null;
        }
        ConversationMemberJpaEntity jpa = new ConversationMemberJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId().value());
        jpa.setClientId(domain.clientId() == null ? null : domain.clientId().value());
        jpa.setSpecialistId(domain.specialistId() == null ? null : domain.specialistId().value());
        jpa.setSenderRoleId(domain.senderRoleId().value());
        jpa.setJoinedAt(domain.joinedAt());
        return jpa;
    }

    public ConversationMember toDomain(ConversationMemberJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return ConversationMember.restore(
                new ConversationMemberId(jpa.getId()),
                new SupportConversationId(jpa.getConversationId()),
                jpa.getClientId() == null ? null : new ClientId(jpa.getClientId()),
                jpa.getSpecialistId() == null ? null : new SpecialistId(jpa.getSpecialistId()),
                new SenderRoleId(jpa.getSenderRoleId()),
                jpa.getJoinedAt());
    }
}
