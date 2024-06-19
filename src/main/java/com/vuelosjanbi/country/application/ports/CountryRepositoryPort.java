package com.vuelosjanbi.country.application.ports;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.country.domain.models.Country;

public interface CountryRepositoryPort {

  Country save(Country country);

  Country update(Country country);

  void delete(int countryId);

  List<Country> findAll();

  Optional<Country> findById(Long countryId);

}
