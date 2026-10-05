package com.carelink.infrastructure.supportconversation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.supportconversation.usecase.*;
import com.carelink.domain.supportconversation.port.repository.SupportConversationRepository;
import com.carelink.infrastructure.supportconversation.adapters.out.persistence.mappers.SupportConversationPersistenceMapper;
import com.carelink.infrastructure.supportconversation.adapters.out.persistence.repositories.SupportConversationJpaRepository;
import com.carelink.infrastructure.supportconversation.adapters.out.persistence.repositories.SupportConversationRepositoryAdapter;

@Configuration
public class SupportConversationBeansConfig {
    @Bean
    public SupportConversationPersistenceMapper supportConversationPersistenceMapper() {
        return new SupportConversationPersistenceMapper();
    }

    @Bean
    public SupportConversationRepository supportConversationRepository(SupportConversationJpaRepository repository, SupportConversationPersistenceMapper mapper) {
        return new SupportConversationRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterSupportConversationUseCase registerSupportConversationUseCase(SupportConversationRepository repository) {
        return new RegisterSupportConversationUseCase(repository);
    }

    @Bean
    public GetSupportConversationByIdUseCase getSupportConversationByIdUseCase(SupportConversationRepository repository) {
        return new GetSupportConversationByIdUseCase(repository);
    }

    @Bean
    public ListSupportConversationUseCase listSupportConversationUseCase(SupportConversationRepository repository) {
        return new ListSupportConversationUseCase(repository);
    }

    @Bean
    public UpdateSupportConversationUseCase updateSupportConversationUseCase(SupportConversationRepository repository) {
        return new UpdateSupportConversationUseCase(repository);
    }

    @Bean
    public DeleteSupportConversationUseCase deleteSupportConversationUseCase(SupportConversationRepository repository) {
        return new DeleteSupportConversationUseCase(repository);
    }
}
