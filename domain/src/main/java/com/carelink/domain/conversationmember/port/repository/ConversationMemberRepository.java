package com.carelink.domain.conversationmember.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.conversationmember.model.aggregate.ConversationMember;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;

public interface ConversationMemberRepository {
    ConversationMember save(ConversationMember aggregate);

    Optional<ConversationMember> findById(ConversationMemberId id);

    List<ConversationMember> findAll();

    void delete(ConversationMember aggregate);
}
