package com.carelink.infrastructure.supportconversationstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.supportconversationstatus.usecase.*;
import com.carelink.domain.supportconversationstatus.port.repository.SupportConversationStatusRepository;
import com.carelink.infrastructure.supportconversationstatus.adapters.out.persistence.mappers.SupportConversationStatusPersistenceMapper;
import com.carelink.infrastructure.supportconversationstatus.adapters.out.persistence.repositories.SupportConversationStatusJpaRepository;
import com.carelink.infrastructure.supportconversationstatus.adapters.out.persistence.repositories.SupportConversationStatusRepositoryAdapter;

@Configuration
public class SupportConversationStatusBeansConfig {
    @Bean
    public SupportConversationStatusPersistenceMapper supportConversationStatusPersistenceMapper() {
        return new SupportConversationStatusPersistenceMapper();
    }

    @Bean
    public SupportConversationStatusRepository supportConversationStatusRepository(SupportConversationStatusJpaRepository repository, SupportConversationStatusPersistenceMapper mapper) {
        return new SupportConversationStatusRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterSupportConversationStatusUseCase registerSupportConversationStatusUseCase(SupportConversationStatusRepository repository) {
        return new RegisterSupportConversationStatusUseCase(repository);
    }

    @Bean
    public GetSupportConversationStatusByIdUseCase getSupportConversationStatusByIdUseCase(SupportConversationStatusRepository repository) {
        return new GetSupportConversationStatusByIdUseCase(repository);
    }

    @Bean
    public ListSupportConversationStatusUseCase listSupportConversationStatusUseCase(SupportConversationStatusRepository repository) {
        return new ListSupportConversationStatusUseCase(repository);
    }

    @Bean
    public UpdateSupportConversationStatusUseCase updateSupportConversationStatusUseCase(SupportConversationStatusRepository repository) {
        return new UpdateSupportConversationStatusUseCase(repository);
    }

    @Bean
    public DeleteSupportConversationStatusUseCase deleteSupportConversationStatusUseCase(SupportConversationStatusRepository repository) {
        return new DeleteSupportConversationStatusUseCase(repository);
    }
}
