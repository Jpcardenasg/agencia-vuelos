package com.vuelosjanbi.airportGate.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vuelosjanbi.airportGate.application.port.out.AirportGateRepositoryPort;
import com.vuelosjanbi.airportGate.domain.models.AirportGate;

@Service
public class AirportGateService {

    private AirportGateRepositoryPort airportGateRepositoryPort;

    public AirportGateService(AirportGateRepositoryPort airportGateRepositoryPort) {
        this.airportGateRepositoryPort = airportGateRepositoryPort;
    }

    public AirportGate createAirportGate(AirportGate airportGate) {
        return airportGateRepositoryPort.save(airportGate);
    }

    public Optional<AirportGate> getAirportGateById(Long id) {
        return airportGateRepositoryPort.findById(id);
    }

    public Optional<AirportGate> getAirportGateByGate(Long id) {
        return airportGateRepositoryPort.findById(id);
    }

    public AirportGate updateAirportGate(AirportGate airportGate) {
        return airportGateRepositoryPort.save(airportGate);
    }

    public void deleteAirportGate(Long id) {
        airportGateRepositoryPort.deleteById(id);
    }

    public List<AirportGate> getAllAirportGates() {
        return airportGateRepositoryPort.findAll();
    }

}
