package com.carelink.domain.supportmessage.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportmessage.event.SupportMessageRegisteredEvent;
import com.carelink.domain.supportmessage.event.SupportMessageUpdatedEvent;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;

public class SupportMessage extends AggregateRoot {
    private final SupportMessageId id;
    private SupportConversationId conversationId;
    private MessageFormatId messageFormatId;
    private ConversationMemberId memberId;
    private String payload;
    private String extraData;
    private final LocalDateTime createdAt;

    private SupportMessage(
            SupportMessageId id,
            SupportConversationId conversationId,
            MessageFormatId messageFormatId,
            ConversationMemberId memberId,
            String payload,
            String extraData,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.messageFormatId = Objects.requireNonNull(messageFormatId, "messageFormatId must not be null");
        this.memberId = Objects.requireNonNull(memberId, "memberId must not be null");
        this.payload = Objects.requireNonNull(payload, "payload must not be null");
        this.extraData = extraData;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
    }

    public static SupportMessage register(
            SupportConversationId conversationId,
            MessageFormatId messageFormatId,
            ConversationMemberId memberId,
            String payload,
            String extraData) {
        SupportMessageId id = SupportMessageId.generate();
        LocalDateTime now = LocalDateTime.now();
        SupportMessage aggregate = new SupportMessage(
                id,
                conversationId,
                messageFormatId,
                memberId,
                payload,
                extraData,
                now);
        aggregate.recordEvent(new SupportMessageRegisteredEvent(id, now));
        return aggregate;
    }

    public static SupportMessage restore(
            SupportMessageId id,
            SupportConversationId conversationId,
            MessageFormatId messageFormatId,
            ConversationMemberId memberId,
            String payload,
            String extraData,
            LocalDateTime createdAt) {
        return new SupportMessage(
                id,
                conversationId,
                messageFormatId,
                memberId,
                payload,
                extraData,
                createdAt);
    }

    public void update(
            SupportConversationId conversationId,
            MessageFormatId messageFormatId,
            ConversationMemberId memberId,
            String payload,
            String extraData) {
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.messageFormatId = Objects.requireNonNull(messageFormatId, "messageFormatId must not be null");
        this.memberId = Objects.requireNonNull(memberId, "memberId must not be null");
        this.payload = Objects.requireNonNull(payload, "payload must not be null");
        this.extraData = extraData;
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new SupportMessageUpdatedEvent(
                this.id,
                this.conversationId,
                this.messageFormatId,
                this.memberId,
                this.payload,
                this.extraData,
                now));
    }

    public SupportMessageId id() {
        return id;
    }

    public SupportConversationId conversationId() {
        return conversationId;
    }

    public MessageFormatId messageFormatId() {
        return messageFormatId;
    }

    public ConversationMemberId memberId() {
        return memberId;
    }

    public String payload() {
        return payload;
    }

    public String extraData() {
        return extraData;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
