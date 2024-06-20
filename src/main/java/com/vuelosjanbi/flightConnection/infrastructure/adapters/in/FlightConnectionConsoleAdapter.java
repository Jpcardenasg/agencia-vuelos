package com.vuelosjanbi.flightConnection.infrastructure.adapters.in;

import java.util.List;
import java.util.Scanner;

import com.vuelosjanbi.flightConnection.application.FlightConnectionService;
import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;

public class FlightConnectionConsoleAdapter {
    private final FlightConnectionService flightConnectionService;

    public FlightConnectionConsoleAdapter(FlightConnectionService flightConnectionService) {
        this.flightConnectionService = flightConnectionService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        List<FlightConnection> flightsConnections = flightConnectionService.getAllFlightsConnections();

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

                    FlightConnection newFlightConnection = new FlightConnection(connectionNumber);
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
