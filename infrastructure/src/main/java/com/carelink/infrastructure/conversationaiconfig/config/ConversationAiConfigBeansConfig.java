package com.carelink.infrastructure.conversationaiconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.conversationaiconfig.usecase.*;
import com.carelink.domain.conversationaiconfig.port.repository.ConversationAiConfigRepository;
import com.carelink.infrastructure.conversationaiconfig.adapters.out.persistence.mappers.ConversationAiConfigPersistenceMapper;
import com.carelink.infrastructure.conversationaiconfig.adapters.out.persistence.repositories.ConversationAiConfigJpaRepository;
import com.carelink.infrastructure.conversationaiconfig.adapters.out.persistence.repositories.ConversationAiConfigRepositoryAdapter;

@Configuration
public class ConversationAiConfigBeansConfig {
    @Bean
    public ConversationAiConfigPersistenceMapper conversationAiConfigPersistenceMapper() {
        return new ConversationAiConfigPersistenceMapper();
    }

    @Bean
    public ConversationAiConfigRepository conversationAiConfigRepository(ConversationAiConfigJpaRepository repository, ConversationAiConfigPersistenceMapper mapper) {
        return new ConversationAiConfigRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterConversationAiConfigUseCase registerConversationAiConfigUseCase(ConversationAiConfigRepository repository) {
        return new RegisterConversationAiConfigUseCase(repository);
    }

    @Bean
    public GetConversationAiConfigByIdUseCase getConversationAiConfigByIdUseCase(ConversationAiConfigRepository repository) {
        return new GetConversationAiConfigByIdUseCase(repository);
    }

    @Bean
    public ListConversationAiConfigUseCase listConversationAiConfigUseCase(ConversationAiConfigRepository repository) {
        return new ListConversationAiConfigUseCase(repository);
    }

    @Bean
    public UpdateConversationAiConfigUseCase updateConversationAiConfigUseCase(ConversationAiConfigRepository repository) {
        return new UpdateConversationAiConfigUseCase(repository);
    }

    @Bean
    public DeleteConversationAiConfigUseCase deleteConversationAiConfigUseCase(ConversationAiConfigRepository repository) {
        return new DeleteConversationAiConfigUseCase(repository);
    }
}
