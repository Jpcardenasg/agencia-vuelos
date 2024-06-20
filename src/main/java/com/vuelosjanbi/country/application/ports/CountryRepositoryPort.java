package com.vuelosjanbi.country.application.ports;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.country.domain.models.Country;

public interface CountryRepositoryPort {

  Country save(Country country);

  void deleteById(String countryId);

  List<Country> findAll();

  Optional<Country> findById(String countryId);

  Optional<Country> findByName(String countryName);

}
