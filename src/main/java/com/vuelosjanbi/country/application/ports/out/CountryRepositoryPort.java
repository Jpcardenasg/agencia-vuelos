package com.vuelosjanbi.country.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.country.domain.models.Country;

public interface CountryRepositoryPort {

  Country save(Country country);

  void deleteById(Long countryId);

  List<Country> findAll();

  Optional<Country> findById(Long countryId);

  Optional<Country> findByName(String countryName);

}
