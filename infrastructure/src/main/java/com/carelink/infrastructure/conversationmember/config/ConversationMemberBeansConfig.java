package com.carelink.infrastructure.conversationmember.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.conversationmember.usecase.*;
import com.carelink.domain.conversationmember.port.repository.ConversationMemberRepository;
import com.carelink.infrastructure.conversationmember.adapters.out.persistence.mappers.ConversationMemberPersistenceMapper;
import com.carelink.infrastructure.conversationmember.adapters.out.persistence.repositories.ConversationMemberJpaRepository;
import com.carelink.infrastructure.conversationmember.adapters.out.persistence.repositories.ConversationMemberRepositoryAdapter;

@Configuration
public class ConversationMemberBeansConfig {
    @Bean
    public ConversationMemberPersistenceMapper conversationMemberPersistenceMapper() {
        return new ConversationMemberPersistenceMapper();
    }

    @Bean
    public ConversationMemberRepository conversationMemberRepository(ConversationMemberJpaRepository repository, ConversationMemberPersistenceMapper mapper) {
        return new ConversationMemberRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterConversationMemberUseCase registerConversationMemberUseCase(ConversationMemberRepository repository) {
        return new RegisterConversationMemberUseCase(repository);
    }

    @Bean
    public GetConversationMemberByIdUseCase getConversationMemberByIdUseCase(ConversationMemberRepository repository) {
        return new GetConversationMemberByIdUseCase(repository);
    }

    @Bean
    public ListConversationMemberUseCase listConversationMemberUseCase(ConversationMemberRepository repository) {
        return new ListConversationMemberUseCase(repository);
    }

    @Bean
    public UpdateConversationMemberUseCase updateConversationMemberUseCase(ConversationMemberRepository repository) {
        return new UpdateConversationMemberUseCase(repository);
    }

    @Bean
    public DeleteConversationMemberUseCase deleteConversationMemberUseCase(ConversationMemberRepository repository) {
        return new DeleteConversationMemberUseCase(repository);
    }
}
