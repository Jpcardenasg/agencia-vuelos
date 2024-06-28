package com.vuelosjanbi.city.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.city.application.ports.CityRepositoryPort;
import com.vuelosjanbi.city.domain.models.City;

@Service
public class CityService {

    @Autowired
    private final CityRepositoryPort cityRepositoryPort;

    public CityService(CityRepositoryPort cityRepository) {
        this.cityRepositoryPort = cityRepository;
    }

    public void createCity(City city) {
        cityRepositoryPort.save(city);
    }

    public void updateCity(City city) {
        cityRepositoryPort.save(city);
    }

    public void deleteCity(Long idCity) {
        cityRepositoryPort.deleteById(idCity);
    }

    public List<City> getAllCities() {
        return cityRepositoryPort.findAll();
    }

    public Optional<City> getCityById(Long idCity) {
        return cityRepositoryPort.findById(idCity);
    }

    public List<City> getCitiesByCountryId(Long countryId) {
        return cityRepositoryPort.findCitiesByCountryId(countryId);
    }

    

}
