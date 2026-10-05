package com.carelink.infrastructure.senderrole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.senderrole.usecase.*;
import com.carelink.domain.senderrole.port.repository.SenderRoleRepository;
import com.carelink.infrastructure.senderrole.adapters.out.persistence.mappers.SenderRolePersistenceMapper;
import com.carelink.infrastructure.senderrole.adapters.out.persistence.repositories.SenderRoleJpaRepository;
import com.carelink.infrastructure.senderrole.adapters.out.persistence.repositories.SenderRoleRepositoryAdapter;

@Configuration
public class SenderRoleBeansConfig {
    @Bean
    public SenderRolePersistenceMapper senderRolePersistenceMapper() {
        return new SenderRolePersistenceMapper();
    }

    @Bean
    public SenderRoleRepository senderRoleRepository(SenderRoleJpaRepository repository, SenderRolePersistenceMapper mapper) {
        return new SenderRoleRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterSenderRoleUseCase registerSenderRoleUseCase(SenderRoleRepository repository) {
        return new RegisterSenderRoleUseCase(repository);
    }

    @Bean
    public GetSenderRoleByIdUseCase getSenderRoleByIdUseCase(SenderRoleRepository repository) {
        return new GetSenderRoleByIdUseCase(repository);
    }

    @Bean
    public ListSenderRoleUseCase listSenderRoleUseCase(SenderRoleRepository repository) {
        return new ListSenderRoleUseCase(repository);
    }

    @Bean
    public UpdateSenderRoleUseCase updateSenderRoleUseCase(SenderRoleRepository repository) {
        return new UpdateSenderRoleUseCase(repository);
    }

    @Bean
    public DeleteSenderRoleUseCase deleteSenderRoleUseCase(SenderRoleRepository repository) {
        return new DeleteSenderRoleUseCase(repository);
    }
}
