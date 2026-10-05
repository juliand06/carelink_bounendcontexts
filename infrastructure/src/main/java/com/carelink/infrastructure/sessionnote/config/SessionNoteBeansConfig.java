package com.carelink.infrastructure.sessionnote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.sessionnote.usecase.*;
import com.carelink.domain.sessionnote.port.repository.SessionNoteRepository;
import com.carelink.infrastructure.sessionnote.adapters.out.persistence.mappers.SessionNotePersistenceMapper;
import com.carelink.infrastructure.sessionnote.adapters.out.persistence.repositories.SessionNoteJpaRepository;
import com.carelink.infrastructure.sessionnote.adapters.out.persistence.repositories.SessionNoteRepositoryAdapter;

@Configuration
public class SessionNoteBeansConfig {
    @Bean
    public SessionNotePersistenceMapper sessionNotePersistenceMapper() {
        return new SessionNotePersistenceMapper();
    }

    @Bean
    public SessionNoteRepository sessionNoteRepository(SessionNoteJpaRepository repository, SessionNotePersistenceMapper mapper) {
        return new SessionNoteRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterSessionNoteUseCase registerSessionNoteUseCase(SessionNoteRepository repository) {
        return new RegisterSessionNoteUseCase(repository);
    }

    @Bean
    public GetSessionNoteByIdUseCase getSessionNoteByIdUseCase(SessionNoteRepository repository) {
        return new GetSessionNoteByIdUseCase(repository);
    }

    @Bean
    public ListSessionNoteUseCase listSessionNoteUseCase(SessionNoteRepository repository) {
        return new ListSessionNoteUseCase(repository);
    }

    @Bean
    public UpdateSessionNoteUseCase updateSessionNoteUseCase(SessionNoteRepository repository) {
        return new UpdateSessionNoteUseCase(repository);
    }

    @Bean
    public DeleteSessionNoteUseCase deleteSessionNoteUseCase(SessionNoteRepository repository) {
        return new DeleteSessionNoteUseCase(repository);
    }
}
