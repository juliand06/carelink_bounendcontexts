package com.carelink.infrastructure.appointmentstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.appointmentstatus.usecase.*;
import com.carelink.domain.appointmentstatus.port.repository.AppointmentStatusRepository;
import com.carelink.infrastructure.appointmentstatus.adapters.out.persistence.mappers.AppointmentStatusPersistenceMapper;
import com.carelink.infrastructure.appointmentstatus.adapters.out.persistence.repositories.AppointmentStatusJpaRepository;
import com.carelink.infrastructure.appointmentstatus.adapters.out.persistence.repositories.AppointmentStatusRepositoryAdapter;

@Configuration
public class AppointmentStatusBeansConfig {
    @Bean
    public AppointmentStatusPersistenceMapper appointmentStatusPersistenceMapper() {
        return new AppointmentStatusPersistenceMapper();
    }

    @Bean
    public AppointmentStatusRepository appointmentStatusRepository(AppointmentStatusJpaRepository repository, AppointmentStatusPersistenceMapper mapper) {
        return new AppointmentStatusRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterAppointmentStatusUseCase registerAppointmentStatusUseCase(AppointmentStatusRepository repository) {
        return new RegisterAppointmentStatusUseCase(repository);
    }

    @Bean
    public GetAppointmentStatusByIdUseCase getAppointmentStatusByIdUseCase(AppointmentStatusRepository repository) {
        return new GetAppointmentStatusByIdUseCase(repository);
    }

    @Bean
    public ListAppointmentStatusUseCase listAppointmentStatusUseCase(AppointmentStatusRepository repository) {
        return new ListAppointmentStatusUseCase(repository);
    }

    @Bean
    public UpdateAppointmentStatusUseCase updateAppointmentStatusUseCase(AppointmentStatusRepository repository) {
        return new UpdateAppointmentStatusUseCase(repository);
    }

    @Bean
    public DeleteAppointmentStatusUseCase deleteAppointmentStatusUseCase(AppointmentStatusRepository repository) {
        return new DeleteAppointmentStatusUseCase(repository);
    }
}
