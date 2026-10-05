package com.carelink.infrastructure.contactrelationshiptype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.contactrelationshiptype.usecase.*;
import com.carelink.domain.contactrelationshiptype.port.repository.ContactRelationshipTypeRepository;
import com.carelink.infrastructure.contactrelationshiptype.adapters.out.persistence.mappers.ContactRelationshipTypePersistenceMapper;
import com.carelink.infrastructure.contactrelationshiptype.adapters.out.persistence.repositories.ContactRelationshipTypeJpaRepository;
import com.carelink.infrastructure.contactrelationshiptype.adapters.out.persistence.repositories.ContactRelationshipTypeRepositoryAdapter;

@Configuration
public class ContactRelationshipTypeBeansConfig {
    @Bean
    public ContactRelationshipTypePersistenceMapper contactRelationshipTypePersistenceMapper() {
        return new ContactRelationshipTypePersistenceMapper();
    }

    @Bean
    public ContactRelationshipTypeRepository contactRelationshipTypeRepository(ContactRelationshipTypeJpaRepository repository, ContactRelationshipTypePersistenceMapper mapper) {
        return new ContactRelationshipTypeRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterContactRelationshipTypeUseCase registerContactRelationshipTypeUseCase(ContactRelationshipTypeRepository repository) {
        return new RegisterContactRelationshipTypeUseCase(repository);
    }

    @Bean
    public GetContactRelationshipTypeByIdUseCase getContactRelationshipTypeByIdUseCase(ContactRelationshipTypeRepository repository) {
        return new GetContactRelationshipTypeByIdUseCase(repository);
    }

    @Bean
    public ListContactRelationshipTypeUseCase listContactRelationshipTypeUseCase(ContactRelationshipTypeRepository repository) {
        return new ListContactRelationshipTypeUseCase(repository);
    }

    @Bean
    public UpdateContactRelationshipTypeUseCase updateContactRelationshipTypeUseCase(ContactRelationshipTypeRepository repository) {
        return new UpdateContactRelationshipTypeUseCase(repository);
    }

    @Bean
    public DeleteContactRelationshipTypeUseCase deleteContactRelationshipTypeUseCase(ContactRelationshipTypeRepository repository) {
        return new DeleteContactRelationshipTypeUseCase(repository);
    }
}
