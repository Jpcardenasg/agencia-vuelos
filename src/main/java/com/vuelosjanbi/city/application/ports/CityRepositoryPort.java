package com.vuelosjanbi.city.application.ports;

import java.util.List;

import com.vuelosjanbi.city.domain.models.City;

public interface CityRepositoryPort {
  City save(City city);

  void deleteById(int cityId);

  List<City> findAll();

  City findById(int cityId);
}
