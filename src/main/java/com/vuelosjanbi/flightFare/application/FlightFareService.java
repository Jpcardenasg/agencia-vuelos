package com.vuelosjanbi.flightFare.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

import com.vuelosjanbi.flightFare.application.ports.out.FlightFareRepositoryPort;
import com.vuelosjanbi.flightFare.domain.models.FlightFare;

@Controller
public class FlightFareService {

    private final FlightFareRepositoryPort flightFareRepositoryPort;

    public FlightFareService(FlightFareRepositoryPort flightFareRepositoryPort) {
        this.flightFareRepositoryPort = flightFareRepositoryPort;
    }

    public FlightFare createFlightFare(FlightFare flightFare) {
        return flightFareRepositoryPort.save(flightFare);
    }

    public FlightFare updateFlightFare(FlightFare flightFare) {
        return flightFareRepositoryPort.save(flightFare);
    }

    public void deleteFlightFare(Long id) {
        flightFareRepositoryPort.deleteById(id);
    }

    public Optional<FlightFare> getFlightFareById(Long id) {
        return flightFareRepositoryPort.findById(id);
    }

    public List<FlightFare> getAllFlightFares() {
        return flightFareRepositoryPort.findAll();
    }

}
