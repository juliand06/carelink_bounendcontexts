package com.carelink.infrastructure.appointmenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.appointmenttype.usecase.*;
import com.carelink.domain.appointmenttype.port.repository.AppointmentTypeRepository;
import com.carelink.infrastructure.appointmenttype.adapters.out.persistence.mappers.AppointmentTypePersistenceMapper;
import com.carelink.infrastructure.appointmenttype.adapters.out.persistence.repositories.AppointmentTypeJpaRepository;
import com.carelink.infrastructure.appointmenttype.adapters.out.persistence.repositories.AppointmentTypeRepositoryAdapter;

@Configuration
public class AppointmentTypeBeansConfig {
    @Bean
    public AppointmentTypePersistenceMapper appointmentTypePersistenceMapper() {
        return new AppointmentTypePersistenceMapper();
    }

    @Bean
    public AppointmentTypeRepository appointmentTypeRepository(AppointmentTypeJpaRepository repository, AppointmentTypePersistenceMapper mapper) {
        return new AppointmentTypeRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterAppointmentTypeUseCase registerAppointmentTypeUseCase(AppointmentTypeRepository repository) {
        return new RegisterAppointmentTypeUseCase(repository);
    }

    @Bean
    public GetAppointmentTypeByIdUseCase getAppointmentTypeByIdUseCase(AppointmentTypeRepository repository) {
        return new GetAppointmentTypeByIdUseCase(repository);
    }

    @Bean
    public ListAppointmentTypeUseCase listAppointmentTypeUseCase(AppointmentTypeRepository repository) {
        return new ListAppointmentTypeUseCase(repository);
    }

    @Bean
    public UpdateAppointmentTypeUseCase updateAppointmentTypeUseCase(AppointmentTypeRepository repository) {
        return new UpdateAppointmentTypeUseCase(repository);
    }

    @Bean
    public DeleteAppointmentTypeUseCase deleteAppointmentTypeUseCase(AppointmentTypeRepository repository) {
        return new DeleteAppointmentTypeUseCase(repository);
    }
}
