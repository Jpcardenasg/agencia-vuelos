package com.vuelosjanbi.flightConnection.application;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.flightConnection.application.ports.out.FlightConnectionsRepositoryPort;
import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;

public class FlightConnectionService {
    private final FlightConnectionsRepositoryPort flightConnectionsRepositoryPort;

    public FlightConnectionService(FlightConnectionsRepositoryPort flightConnectionsRepositoryPort) {
        this.flightConnectionsRepositoryPort = flightConnectionsRepositoryPort;
    }

    public FlightConnection createFlightConnection(FlightConnection flightConnection) {
        return flightConnectionsRepositoryPort.save(flightConnection);
    }

    public FlightConnection updateFlightConnection(FlightConnection flightConnection) {
        return flightConnectionsRepositoryPort.save(flightConnection);
    }

    public void deleteFlightConnection(Long idConnection) {
        flightConnectionsRepositoryPort.deleteById(idConnection);
    }

    public Optional<FlightConnection> getConnectionById(Long idConnection) {
        return flightConnectionsRepositoryPort.findById(idConnection);
    }

    public List<FlightConnection> getConnectionByPlaneId(Long planeId) {
        return flightConnectionsRepositoryPort.findByPlaneId(planeId);
    }

    public List<FlightConnection> getAllFlightsConnections() {
        return flightConnectionsRepositoryPort.findAll();
    }

}
