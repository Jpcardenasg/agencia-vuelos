package com.vuelosjanbi.country.application;

import java.util.List;

import com.vuelosjanbi.country.domain.models.Country;
import com.vuelosjanbi.country.infrastructure.repository.CountryRepository;

public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void createCountry(Country country) {
        countryRepository.save(country);
    }

    public void updateCountry(Country country) {
        countryRepository.update(country);
    }

    public void deleteCountry(int idCountry) {
        countryRepository.delete(idCountry);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

}
