package com.carelink.infrastructure.contactphonenumber.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.contactphonenumber.usecase.*;
import com.carelink.domain.contactphonenumber.port.repository.ContactPhoneNumberRepository;
import com.carelink.infrastructure.contactphonenumber.adapters.out.persistence.mappers.ContactPhoneNumberPersistenceMapper;
import com.carelink.infrastructure.contactphonenumber.adapters.out.persistence.repositories.ContactPhoneNumberJpaRepository;
import com.carelink.infrastructure.contactphonenumber.adapters.out.persistence.repositories.ContactPhoneNumberRepositoryAdapter;

@Configuration
public class ContactPhoneNumberBeansConfig {
    @Bean
    public ContactPhoneNumberPersistenceMapper contactPhoneNumberPersistenceMapper() {
        return new ContactPhoneNumberPersistenceMapper();
    }

    @Bean
    public ContactPhoneNumberRepository contactPhoneNumberRepository(ContactPhoneNumberJpaRepository repository, ContactPhoneNumberPersistenceMapper mapper) {
        return new ContactPhoneNumberRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterContactPhoneNumberUseCase registerContactPhoneNumberUseCase(ContactPhoneNumberRepository repository) {
        return new RegisterContactPhoneNumberUseCase(repository);
    }

    @Bean
    public GetContactPhoneNumberByIdUseCase getContactPhoneNumberByIdUseCase(ContactPhoneNumberRepository repository) {
        return new GetContactPhoneNumberByIdUseCase(repository);
    }

    @Bean
    public ListContactPhoneNumberUseCase listContactPhoneNumberUseCase(ContactPhoneNumberRepository repository) {
        return new ListContactPhoneNumberUseCase(repository);
    }

    @Bean
    public UpdateContactPhoneNumberUseCase updateContactPhoneNumberUseCase(ContactPhoneNumberRepository repository) {
        return new UpdateContactPhoneNumberUseCase(repository);
    }

    @Bean
    public DeleteContactPhoneNumberUseCase deleteContactPhoneNumberUseCase(ContactPhoneNumberRepository repository) {
        return new DeleteContactPhoneNumberUseCase(repository);
    }
}
