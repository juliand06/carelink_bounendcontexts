package com.carelink.infrastructure.department.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.department.usecase.*;
import com.carelink.domain.department.port.repository.DepartmentRepository;
import com.carelink.infrastructure.department.adapters.out.persistence.mappers.DepartmentPersistenceMapper;
import com.carelink.infrastructure.department.adapters.out.persistence.repositories.DepartmentJpaRepository;
import com.carelink.infrastructure.department.adapters.out.persistence.repositories.DepartmentRepositoryAdapter;

@Configuration
public class DepartmentBeansConfig {
    @Bean
    public DepartmentPersistenceMapper departmentPersistenceMapper() {
        return new DepartmentPersistenceMapper();
    }

    @Bean
    public DepartmentRepository departmentRepository(DepartmentJpaRepository repository, DepartmentPersistenceMapper mapper) {
        return new DepartmentRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterDepartmentUseCase registerDepartmentUseCase(DepartmentRepository repository) {
        return new RegisterDepartmentUseCase(repository);
    }

    @Bean
    public GetDepartmentByIdUseCase getDepartmentByIdUseCase(DepartmentRepository repository) {
        return new GetDepartmentByIdUseCase(repository);
    }

    @Bean
    public ListDepartmentUseCase listDepartmentUseCase(DepartmentRepository repository) {
        return new ListDepartmentUseCase(repository);
    }

    @Bean
    public UpdateDepartmentUseCase updateDepartmentUseCase(DepartmentRepository repository) {
        return new UpdateDepartmentUseCase(repository);
    }

    @Bean
    public DeleteDepartmentUseCase deleteDepartmentUseCase(DepartmentRepository repository) {
        return new DeleteDepartmentUseCase(repository);
    }
}
