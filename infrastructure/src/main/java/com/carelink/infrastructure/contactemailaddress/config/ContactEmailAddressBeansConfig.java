package com.carelink.infrastructure.contactemailaddress.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.contactemailaddress.usecase.*;
import com.carelink.domain.contactemailaddress.port.repository.ContactEmailAddressRepository;
import com.carelink.infrastructure.contactemailaddress.adapters.out.persistence.mappers.ContactEmailAddressPersistenceMapper;
import com.carelink.infrastructure.contactemailaddress.adapters.out.persistence.repositories.ContactEmailAddressJpaRepository;
import com.carelink.infrastructure.contactemailaddress.adapters.out.persistence.repositories.ContactEmailAddressRepositoryAdapter;

@Configuration
public class ContactEmailAddressBeansConfig {
    @Bean
    public ContactEmailAddressPersistenceMapper contactEmailAddressPersistenceMapper() {
        return new ContactEmailAddressPersistenceMapper();
    }

    @Bean
    public ContactEmailAddressRepository contactEmailAddressRepository(ContactEmailAddressJpaRepository repository, ContactEmailAddressPersistenceMapper mapper) {
        return new ContactEmailAddressRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterContactEmailAddressUseCase registerContactEmailAddressUseCase(ContactEmailAddressRepository repository) {
        return new RegisterContactEmailAddressUseCase(repository);
    }

    @Bean
    public GetContactEmailAddressByIdUseCase getContactEmailAddressByIdUseCase(ContactEmailAddressRepository repository) {
        return new GetContactEmailAddressByIdUseCase(repository);
    }

    @Bean
    public ListContactEmailAddressUseCase listContactEmailAddressUseCase(ContactEmailAddressRepository repository) {
        return new ListContactEmailAddressUseCase(repository);
    }

    @Bean
    public UpdateContactEmailAddressUseCase updateContactEmailAddressUseCase(ContactEmailAddressRepository repository) {
        return new UpdateContactEmailAddressUseCase(repository);
    }

    @Bean
    public DeleteContactEmailAddressUseCase deleteContactEmailAddressUseCase(ContactEmailAddressRepository repository) {
        return new DeleteContactEmailAddressUseCase(repository);
    }
}
