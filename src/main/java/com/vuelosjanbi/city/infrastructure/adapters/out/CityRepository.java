package com.vuelosjanbi.city.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.city.application.ports.CityRepositoryPort;
import com.vuelosjanbi.city.domain.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>, CityRepositoryPort {

}
