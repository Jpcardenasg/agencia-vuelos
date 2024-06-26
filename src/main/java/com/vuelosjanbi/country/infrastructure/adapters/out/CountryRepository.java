package com.vuelosjanbi.country.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.country.application.ports.out.CountryRepositoryPort;
import com.vuelosjanbi.country.domain.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>, CountryRepositoryPort {

}
