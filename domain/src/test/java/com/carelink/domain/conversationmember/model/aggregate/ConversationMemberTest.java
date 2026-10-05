package com.carelink.domain.conversationmember.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.conversationmember.event.ConversationMemberRegisteredEvent;
import com.carelink.domain.conversationmember.event.ConversationMemberUpdatedEvent;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import org.junit.jupiter.api.Test;

class ConversationMemberTest {
    private static ConversationMember sample() {
        return ConversationMember.register(
                SupportConversationId.generate(),
                ClientId.generate(),
                null,
                SenderRoleId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        ConversationMember aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        ConversationMemberRegisteredEvent event = assertInstanceOf(ConversationMemberRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        ConversationMember aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                SupportConversationId.generate(),
                ClientId.generate(),
                null,
                SenderRoleId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        ConversationMemberUpdatedEvent event = assertInstanceOf(ConversationMemberUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        ConversationMember original = sample();
        ConversationMember restored = ConversationMember.restore(
                original.id(),
                original.conversationId(),
                original.clientId(),
                original.specialistId(),
                original.senderRoleId(),
                original.joinedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
