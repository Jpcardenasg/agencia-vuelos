package com.vuelosjanbi.country.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.vuelosjanbi.country.domain.models.Country;
import com.vuelosjanbi.country.application.ports.out.CountryRepositoryPort;

@Service
public class CountryService {

    @Autowired
    private final CountryRepositoryPort countryRepositoryPort;

    public CountryService(CountryRepositoryPort countryRepositoryPort) {
        this.countryRepositoryPort = countryRepositoryPort;
    }

    public Country createCountry(Country country) {
        return countryRepositoryPort.save(country);
    }

    public Country updateCountry(Country country) {
        return countryRepositoryPort.save(country);
    }

    public void deleteCountry(Long countryId) {
        countryRepositoryPort.deleteById(countryId);
    }

    public Optional<Country> getCountryById(Long countryId) {
        return countryRepositoryPort.findById(countryId);
    }

    public Optional<Country> getCountryByName(String countryName) {
        return countryRepositoryPort.findByName(countryName);
    }

    public List<Country> getAllCountries() {
        return countryRepositoryPort.findAll();
    }

}
