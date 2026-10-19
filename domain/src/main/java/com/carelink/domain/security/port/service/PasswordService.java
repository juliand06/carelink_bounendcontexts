package com.carelink.domain.security.port.service;

public interface PasswordService {
    String hash(String plainPassword);
    boolean matches(String plainPassword, String hashedPassword);
}
