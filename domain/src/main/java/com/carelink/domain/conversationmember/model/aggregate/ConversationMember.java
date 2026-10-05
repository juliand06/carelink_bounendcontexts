package com.carelink.domain.conversationmember.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.conversationmember.event.ConversationMemberRegisteredEvent;
import com.carelink.domain.conversationmember.event.ConversationMemberUpdatedEvent;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;

public class ConversationMember extends AggregateRoot {
    private final ConversationMemberId id;
    private SupportConversationId conversationId;
    private ClientId clientId;
    private SpecialistId specialistId;
    private SenderRoleId senderRoleId;
    private final LocalDateTime joinedAt;

    private ConversationMember(
            ConversationMemberId id,
            SupportConversationId conversationId,
            ClientId clientId,
            SpecialistId specialistId,
            SenderRoleId senderRoleId,
            LocalDateTime joinedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.clientId = clientId;
        this.specialistId = specialistId;
        this.senderRoleId = Objects.requireNonNull(senderRoleId, "senderRoleId must not be null");
        this.joinedAt = Objects.requireNonNull(joinedAt, "joinedAt must not be null");
        requireSingleActor(clientId, specialistId);
    }

    public static ConversationMember register(
            SupportConversationId conversationId,
            ClientId clientId,
            SpecialistId specialistId,
            SenderRoleId senderRoleId) {
        ConversationMemberId id = ConversationMemberId.generate();
        LocalDateTime now = LocalDateTime.now();
        ConversationMember aggregate = new ConversationMember(
                id,
                conversationId,
                clientId,
                specialistId,
                senderRoleId,
                now);
        aggregate.recordEvent(new ConversationMemberRegisteredEvent(id, now));
        return aggregate;
    }

    public static ConversationMember restore(
            ConversationMemberId id,
            SupportConversationId conversationId,
            ClientId clientId,
            SpecialistId specialistId,
            SenderRoleId senderRoleId,
            LocalDateTime joinedAt) {
        return new ConversationMember(
                id,
                conversationId,
                clientId,
                specialistId,
                senderRoleId,
                joinedAt);
    }

    public void update(
            SupportConversationId conversationId,
            ClientId clientId,
            SpecialistId specialistId,
            SenderRoleId senderRoleId) {
        requireSingleActor(clientId, specialistId);
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.clientId = clientId;
        this.specialistId = specialistId;
        this.senderRoleId = Objects.requireNonNull(senderRoleId, "senderRoleId must not be null");
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new ConversationMemberUpdatedEvent(
                this.id,
                this.conversationId,
                this.clientId,
                this.specialistId,
                this.senderRoleId,
                now));
    }

    private static void requireSingleActor(ClientId clientId, SpecialistId specialistId) {
        if ((clientId == null) == (specialistId == null)) {
            throw new IllegalArgumentException("exactly one of clientId or specialistId must be provided");
        }
    }

    public ConversationMemberId id() {
        return id;
    }

    public SupportConversationId conversationId() {
        return conversationId;
    }

    public ClientId clientId() {
        return clientId;
    }

    public SpecialistId specialistId() {
        return specialistId;
    }

    public SenderRoleId senderRoleId() {
        return senderRoleId;
    }

    public LocalDateTime joinedAt() {
        return joinedAt;
    }
}
