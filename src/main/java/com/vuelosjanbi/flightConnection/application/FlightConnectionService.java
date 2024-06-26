package com.vuelosjanbi.flightConnection.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vuelosjanbi.flightConnection.application.ports.out.FlightConnectionRepositoryPort;
import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;
import com.vuelosjanbi.trip.domain.models.Trip;

@Service
public class FlightConnectionService {
    private final FlightConnectionRepositoryPort flightConnectionsRepositoryPort;

    public FlightConnectionService(FlightConnectionRepositoryPort flightConnectionsRepositoryPort) {
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

    public List<FlightConnection> getAllFlightConnections() {
        return flightConnectionsRepositoryPort.findAll();
    }

    public List<FlightConnection> getConnectionByPlanePlate(String plate) {
        return flightConnectionsRepositoryPort.findByPlanePlate(plate);
    }

    public Optional<FlightConnection> getConnectionByTripId(Long tripId) {
        return flightConnectionsRepositoryPort.findByTripId(tripId);
    }

    public List<FlightConnection> getConnectionByTripId(Trip trip) {
        return flightConnectionsRepositoryPort.findByTrip(trip);
    }

}
