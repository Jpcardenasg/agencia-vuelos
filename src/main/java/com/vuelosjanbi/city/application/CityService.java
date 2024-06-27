package com.vuelosjanbi.city.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.city.application.ports.CityRepositoryPort;
import com.vuelosjanbi.city.domain.models.City;

@Service
public class CityService {

    @Autowired
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

    public City getCityById(Long idCity) {
        return CityRepositoryPort.findById(idCity).orElse(null);
    }

    

}
