package com.vuelosjanbi.city.application;

import java.util.List;

import com.vuelosjanbi.city.application.ports.CityRepositoryPort;
import com.vuelosjanbi.city.domain.models.City;

public class CityService {
    private final CityRepositoryPort CityRepositoryPort;

    public CityService(CityRepositoryPort cityRepository) {
        this.CityRepositoryPort = cityRepository;
    }

    public void createCity(City city) {
        CityRepositoryPort.save(city);
    }

    public void updateCity(City city) {
        CityRepositoryPort.save(city);
    }

    public void deleteCity(Long idCity) {
        CityRepositoryPort.deleteById(idCity);
    }

    public List<City> getAllCities() {
        return CityRepositoryPort.findAll();
    }

}
