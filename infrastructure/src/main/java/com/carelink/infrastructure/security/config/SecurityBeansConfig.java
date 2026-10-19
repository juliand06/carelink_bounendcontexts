package com.carelink.infrastructure.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.security.usecase.AssignRoleUseCase;
import com.carelink.application.security.usecase.ChangePasswordUseCase;
import com.carelink.application.security.usecase.GetCurrentUserUseCase;
import com.carelink.application.security.usecase.LoginUseCase;
import com.carelink.application.security.usecase.LogoutUseCase;
import com.carelink.application.security.usecase.RefreshTokenUseCase;
import com.carelink.application.security.usecase.RegisterUserUseCase;
import com.carelink.domain.security.port.repository.RefreshTokenRepository;
import com.carelink.domain.security.port.repository.RoleRepository;
import com.carelink.domain.security.port.repository.UserRepository;
import com.carelink.domain.security.port.service.PasswordService;
import com.carelink.domain.security.port.service.TokenService;
import com.carelink.infrastructure.security.adapters.out.persistence.mappers.RefreshTokenPersistenceMapper;
import com.carelink.infrastructure.security.adapters.out.persistence.mappers.RolePersistenceMapper;
import com.carelink.infrastructure.security.adapters.out.persistence.mappers.UserPersistenceMapper;
import com.carelink.infrastructure.security.adapters.out.persistence.repositories.RefreshTokenJpaRepository;
import com.carelink.infrastructure.security.adapters.out.persistence.repositories.RefreshTokenRepositoryAdapter;
import com.carelink.infrastructure.security.adapters.out.persistence.repositories.RoleJpaRepository;
import com.carelink.infrastructure.security.adapters.out.persistence.repositories.RoleRepositoryAdapter;
import com.carelink.infrastructure.security.adapters.out.persistence.repositories.UserJpaRepository;
import com.carelink.infrastructure.security.adapters.out.persistence.repositories.UserRepositoryAdapter;

@Configuration
public class SecurityBeansConfig {

    @Bean
    public RolePersistenceMapper securityRolePersistenceMapper() {
        return new RolePersistenceMapper();
    }

    @Bean
    public UserPersistenceMapper securityUserPersistenceMapper(RolePersistenceMapper mapper) {
        return new UserPersistenceMapper(mapper);
    }

    @Bean
    public RefreshTokenPersistenceMapper securityRefreshTokenPersistenceMapper() {
        return new RefreshTokenPersistenceMapper();
    }

    @Bean
    public RoleRepository securityRoleRepository(RoleJpaRepository repo, RolePersistenceMapper mapper) {
        return new RoleRepositoryAdapter(repo, mapper);
    }

    @Bean
    public UserRepository securityUserRepository(UserJpaRepository repo, UserPersistenceMapper mapper) {
        return new UserRepositoryAdapter(repo, mapper);
    }

    @Bean
    public RefreshTokenRepository securityRefreshTokenRepository(RefreshTokenJpaRepository repo,
                                                                 RefreshTokenPersistenceMapper mapper) {
        return new RefreshTokenRepositoryAdapter(repo, mapper);
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRepository userRepository,
                                                  RoleRepository roleRepository,
                                                  PasswordService passwordService) {
        return new RegisterUserUseCase(userRepository, roleRepository, passwordService);
    }

    @Bean
    public LoginUseCase loginUseCase(UserRepository userRepository,
                                     PasswordService passwordService,
                                     TokenService tokenService,
                                     RefreshTokenRepository refreshTokenRepository,
                                     @Value("${jwt.access-token-expiration:900000}") long accessTokenExpiration) {
        return new LoginUseCase(userRepository, passwordService, tokenService, refreshTokenRepository, accessTokenExpiration);
    }

    @Bean
    public RefreshTokenUseCase refreshTokenUseCase(RefreshTokenRepository refreshTokenRepository,
                                                   UserRepository userRepository,
                                                   TokenService tokenService,
                                                   @Value("${jwt.access-token-expiration:900000}") long accessTokenExpiration) {
        return new RefreshTokenUseCase(refreshTokenRepository, userRepository, tokenService, accessTokenExpiration);
    }

    @Bean
    public LogoutUseCase logoutUseCase(RefreshTokenRepository refreshTokenRepository) {
        return new LogoutUseCase(refreshTokenRepository);
    }

    @Bean
    public GetCurrentUserUseCase getCurrentUserUseCase(UserRepository userRepository) {
        return new GetCurrentUserUseCase(userRepository);
    }

    @Bean
    public ChangePasswordUseCase changePasswordUseCase(UserRepository userRepository,
                                                       PasswordService passwordService) {
        return new ChangePasswordUseCase(userRepository, passwordService);
    }

    @Bean
    public AssignRoleUseCase assignRoleUseCase(UserRepository userRepository,
                                               RoleRepository roleRepository) {
        return new AssignRoleUseCase(userRepository, roleRepository);
    }
}
