package com.vuelosjanbi.city.application.ports;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.city.domain.models.City;

public interface CityRepositoryPort {
  City save(City city);

  void deleteById(Long cityId);

  List<City> findAll();

  Optional<City> findById(Long cityId);

  List<City> findCitiesByCountryId(Long countryId);
}
