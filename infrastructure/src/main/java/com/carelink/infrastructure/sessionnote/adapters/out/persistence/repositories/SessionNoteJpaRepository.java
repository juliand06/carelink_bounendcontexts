package com.carelink.infrastructure.sessionnote.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.sessionnote.adapters.out.persistence.entity.SessionNoteJpaEntity;

public interface SessionNoteJpaRepository extends JpaRepository<SessionNoteJpaEntity, UUID> {
}
