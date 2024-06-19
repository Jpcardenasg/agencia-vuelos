package com.vuelosjanbi.city.infrastructure.repository;

import java.util.List;

import com.vuelosjanbi.city.domain.models.City;

public interface CityRepository {
    void save(City city);

    void update(City city);

    void delete(int cityId);

    List<City> findAll();
}
