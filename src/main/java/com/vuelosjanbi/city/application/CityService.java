package com.vuelosjanbi.city.application;

import java.util.List;

import com.vuelosjanbi.city.domain.models.City;
import com.vuelosjanbi.city.infrastructure.repository.CityRepository;

public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void createCity(City city) {
        cityRepository.save(city);
    }

    public void updateCity(City city) {
        cityRepository.update(city);
    }

    public void deleteCity(int idCity) {
        cityRepository.delete(idCity);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

}
