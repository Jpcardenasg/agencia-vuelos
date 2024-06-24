package com.vuelosjanbi.airline.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.airline.application.ports.out.AirlineRepositoryPort;
import com.vuelosjanbi.airline.domain.models.Airline;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepositoryPort airlineRepositoryPort;

    public AirlineService(AirlineRepositoryPort airlineRepositoryPort) {
        this.airlineRepositoryPort = airlineRepositoryPort;
    }

    public Airline createAirline(Airline airline) {
        return airlineRepositoryPort.save(airline);
    }

    public Airline updateAirline(Airline airline) {
        return airlineRepositoryPort.save(airline);
    }

    public void deleteAirlineById(Long id) {
        airlineRepositoryPort.deleteById(id);
    }

    public Optional<Airline> getAirlineById(Long id) {
        return airlineRepositoryPort.findById(id);
    }

    public Optional<Airline> getAirlinesByName(String name) {
        return airlineRepositoryPort.findByName(name);
    }

    public List<Airline> getAllAirlines() {
        return airlineRepositoryPort.findAll();
    }

}
