package com.vuelosjanbi.flightConnection.infrastructure.adapters.in;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.vuelosjanbi.airport.application.AirportService;
import com.vuelosjanbi.airport.domain.models.Airport;
import com.vuelosjanbi.flightConnection.application.FlightConnectionService;
import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;
import com.vuelosjanbi.plane.application.PlaneService;
import com.vuelosjanbi.plane.domain.models.Plane;
import com.vuelosjanbi.trip.application.TripService;
import com.vuelosjanbi.trip.domain.models.Trip;

public class FlightConnectionConsoleAdapter {

    @Autowired
    private FlightConnectionService flightConnectionService;

    @Autowired
    private TripService tripService;

    @Autowired
    private PlaneService planeService;

    @Autowired
    private AirportService airportService;

    public FlightConnectionConsoleAdapter(FlightConnectionService flightConnectionService) {
        this.flightConnectionService = flightConnectionService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        List<FlightConnection> flightsConnections = flightConnectionService.getAllFlightConnections();

        while (true) {
            System.out.println("1. Create Connection.");
            System.out.println("2. Update Connection.");
            System.out.println("3. Delete Connection.");
            System.out.println("4. List all Connections.");
            System.out.println("5. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Type the connection number:");
                    String connectionNumber = scanner.nextLine();
                    System.out.println("Choose the ID of the trip you want to assign:");
                    List<Trip> tripList = tripService.getAllTrips();
                    tripList.forEach(trip -> {
                        System.out.printf("ID: %d, Trip Date: %s", trip.getId(), trip.getTripDate().toString());
                    });
                    Long tripId = scanner.nextLong();
                    scanner.nextLine();
                    List<Plane> planes = planeService.getAllPlanes();
                    planes.forEach(plane -> {
                        System.out.printf("ID: %d, Plane Model: %s", plane.getId(), plane.getPlate());
                    });
                    System.out.println("Choose the ID of the plane you want to assign:");
                    Long planeId = scanner.nextLong();
                    scanner.nextLine();
                    List<Airport> airports = airportService.getAllAirports();
                    airports.forEach(airport -> {
                        System.out.printf("ID: %d, Airport Name: %s", airport.getId(), airport.getName());
                    });
                    System.out.println("Choose the ID of the origin airport:");
                    Long originAirportId = scanner.nextLong();
                    System.out.println("Choose the ID of the destination airport:");
                    Long destinationAirportId = scanner.nextLong();
                    scanner.nextLine();

                    FlightConnection newFlightConnection = new FlightConnection(connectionNumber);
                    newFlightConnection.setTrip(tripService.getTripById(tripId));
                    newFlightConnection.setPlane(planeService.getPlaneById(planeId));
                    newFlightConnection.setOriginAirport(airportService.getAirportById(originAirportId));
                    newFlightConnection.setDestinationAirport(airportService.getAirportById(destinationAirportId));
                    flightConnectionService.createFlightConnection(newFlightConnection);
                    break;

                case 2:
                    System.out.println("Choose the ID of the flight connection you want to modify");
                    flightsConnections.forEach(fconn -> {
                        System.out.printf("ID: %d, Connection Number: %s", fconn.getId(), fconn.getConnectionNumber());
                    });

                    Long updateFlighConnection = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Type the new connection number:");
                    String newConnectionNumber = scanner.nextLine();

                    FlightConnection updatedfFlightConnection = new FlightConnection(updateFlighConnection,
                            newConnectionNumber);
                    flightConnectionService.updateFlightConnection(updatedfFlightConnection);
                    break;

                case 3:
                    System.out.println("Choose the Flight Connection ID to delete:");
                    flightsConnections.forEach(fconn -> {
                        System.out.printf("ID: %d, Connection Number: %s", fconn.getId(), fconn.getConnectionNumber());
                    });

                    Long deleteFlightConnection = scanner.nextLong();
                    scanner.nextLine();
                    flightConnectionService.deleteFlightConnection(deleteFlightConnection);
                    break;

                case 4:
                    System.out.println("List of Countries:");
                    flightsConnections.forEach(fconn -> {
                        System.out.printf("ID: %d, Connection Number: %s", fconn.getId(), fconn.getConnectionNumber());
                    });
                    break;

                case 5:
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option, please try again");
            }
        }
    }
}
