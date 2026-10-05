package com.carelink.infrastructure.country.adapters.out.persistence.mappers;

import com.carelink.domain.country.model.aggregate.Country;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.infrastructure.country.adapters.out.persistence.entity.CountryJpaEntity;

public class CountryPersistenceMapper {
    public CountryJpaEntity toJpa(Country domain) {
        if (domain == null) {
            return null;
        }
        CountryJpaEntity jpa = new CountryJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setName(domain.name());
        jpa.setIsoCode(domain.isoCode());
        jpa.setNotes(domain.notes());
        jpa.setEnabled(domain.enabled());
        jpa.setDialPrefix(domain.dialPrefix());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public Country toDomain(CountryJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return Country.restore(
                new CountryId(jpa.getId()),
                jpa.getName(),
                jpa.getIsoCode(),
                jpa.getNotes(),
                jpa.getEnabled(),
                jpa.getDialPrefix(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
