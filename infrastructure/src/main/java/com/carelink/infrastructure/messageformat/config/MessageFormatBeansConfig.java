package com.carelink.infrastructure.messageformat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.messageformat.usecase.*;
import com.carelink.domain.messageformat.port.repository.MessageFormatRepository;
import com.carelink.infrastructure.messageformat.adapters.out.persistence.mappers.MessageFormatPersistenceMapper;
import com.carelink.infrastructure.messageformat.adapters.out.persistence.repositories.MessageFormatJpaRepository;
import com.carelink.infrastructure.messageformat.adapters.out.persistence.repositories.MessageFormatRepositoryAdapter;

@Configuration
public class MessageFormatBeansConfig {
    @Bean
    public MessageFormatPersistenceMapper messageFormatPersistenceMapper() {
        return new MessageFormatPersistenceMapper();
    }

    @Bean
    public MessageFormatRepository messageFormatRepository(MessageFormatJpaRepository repository, MessageFormatPersistenceMapper mapper) {
        return new MessageFormatRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterMessageFormatUseCase registerMessageFormatUseCase(MessageFormatRepository repository) {
        return new RegisterMessageFormatUseCase(repository);
    }

    @Bean
    public GetMessageFormatByIdUseCase getMessageFormatByIdUseCase(MessageFormatRepository repository) {
        return new GetMessageFormatByIdUseCase(repository);
    }

    @Bean
    public ListMessageFormatUseCase listMessageFormatUseCase(MessageFormatRepository repository) {
        return new ListMessageFormatUseCase(repository);
    }

    @Bean
    public UpdateMessageFormatUseCase updateMessageFormatUseCase(MessageFormatRepository repository) {
        return new UpdateMessageFormatUseCase(repository);
    }

    @Bean
    public DeleteMessageFormatUseCase deleteMessageFormatUseCase(MessageFormatRepository repository) {
        return new DeleteMessageFormatUseCase(repository);
    }
}
