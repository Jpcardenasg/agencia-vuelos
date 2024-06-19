package com.vuelosjanbi.country.infrastructure.repository;

import java.util.List;

import com.vuelosjanbi.country.domain.models.Country;

public interface CountryRepository {
    void save(Country country);

    void update(Country country);

    void delete(int countryId);

    List<Country> findAll();
}
