package com.carelink.infrastructure.supportmessage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.supportmessage.usecase.*;
import com.carelink.domain.supportmessage.port.repository.SupportMessageRepository;
import com.carelink.infrastructure.supportmessage.adapters.out.persistence.mappers.SupportMessagePersistenceMapper;
import com.carelink.infrastructure.supportmessage.adapters.out.persistence.repositories.SupportMessageJpaRepository;
import com.carelink.infrastructure.supportmessage.adapters.out.persistence.repositories.SupportMessageRepositoryAdapter;

@Configuration
public class SupportMessageBeansConfig {
    @Bean
    public SupportMessagePersistenceMapper supportMessagePersistenceMapper() {
        return new SupportMessagePersistenceMapper();
    }

    @Bean
    public SupportMessageRepository supportMessageRepository(SupportMessageJpaRepository repository, SupportMessagePersistenceMapper mapper) {
        return new SupportMessageRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterSupportMessageUseCase registerSupportMessageUseCase(SupportMessageRepository repository) {
        return new RegisterSupportMessageUseCase(repository);
    }

    @Bean
    public GetSupportMessageByIdUseCase getSupportMessageByIdUseCase(SupportMessageRepository repository) {
        return new GetSupportMessageByIdUseCase(repository);
    }

    @Bean
    public ListSupportMessageUseCase listSupportMessageUseCase(SupportMessageRepository repository) {
        return new ListSupportMessageUseCase(repository);
    }

    @Bean
    public UpdateSupportMessageUseCase updateSupportMessageUseCase(SupportMessageRepository repository) {
        return new UpdateSupportMessageUseCase(repository);
    }

    @Bean
    public DeleteSupportMessageUseCase deleteSupportMessageUseCase(SupportMessageRepository repository) {
        return new DeleteSupportMessageUseCase(repository);
    }
}
