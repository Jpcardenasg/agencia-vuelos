package com.vuelosjanbi.airportGate.infrastructure.adapters.in;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.airport.application.AirportService;
import com.vuelosjanbi.airportGate.application.AirportGateService;
import com.vuelosjanbi.airportGate.domain.models.AirportGate;
import com.vuelosjanbi.airport.domain.models.Airport;

@Controller
public class AirportGateConsoleAdapter {

    @Autowired
    private AirportGateService airportGateService;
    @Autowired
    private AirportService airportService;

    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create an Airport Gate.");
            System.out.println("2. Update an Airport Gate.");
            System.out.println("3. Delete an Airport Gate.");
            System.out.println("4. List all Airport Gates.");
            System.out.println("5. Find Airport Gate by ID.");
            System.out.println("6. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAirportGate(scanner);
                    break;

                case 2:
                    updateAirportGate(scanner);
                    break;

                case 3:
                    deleteAirportGate(scanner);
                    break;

                case 4:
                    listAllAirportGates();
                    break;

                case 5:
                    findAirportGateById(scanner);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void createAirportGate(Scanner scanner) {
        System.out.println("Type the name of the gate:");
        String newGate = scanner.nextLine();

        Airport chosenAirport = selectAirport(scanner);

        AirportGate airportGate = new AirportGate();
        airportGate.setGate(newGate);
        airportGate.setAirport(chosenAirport);

        airportGateService.createAirportGate(airportGate);
        System.out.println("Airport Gate created successfully!");
    }

    private void updateAirportGate(Scanner scanner) {

        AirportGate chosenAirportGate = selectAirportGate(scanner);

        System.out.println("Type the new name of the gate:");
        String updatedGateName = scanner.nextLine();

        Airport updatedAirport = selectAirport(scanner);

        chosenAirportGate.setGate(updatedGateName);
        chosenAirportGate.setAirport(updatedAirport);

        airportGateService.updateAirportGate(chosenAirportGate);
        System.out.println("Airport Gate updated successfully!");
    }

    private void deleteAirportGate(Scanner scanner) {
        AirportGate chosenAirportGate = selectAirportGate(scanner);

        airportGateService.deleteAirportGate(chosenAirportGate.getId());
        System.out.println("Airport Gate deleted successfully!");
    }

    private void listAllAirportGates() {
        airportGateService.getAllAirportGates().forEach(gate -> {
            System.out.printf("ID: %d, Gate: %s, Airport: %s \n", gate.getId(), gate.getGate(),
                    gate.getAirport().getName());
        });
    }

    private void findAirportGateById(Scanner scanner) {
        System.out.println("Enter the ID of the airport gate you want to find:");
        Long gateId = scanner.nextLong();
        scanner.nextLine();

        Optional<AirportGate> optionalAirportGate = airportGateService.getAirportGateById(gateId);
        if (optionalAirportGate.isPresent()) {
            AirportGate airportGate = optionalAirportGate.get();
            System.out.printf("ID: %d, Gate: %s, Airport: %s \n", airportGate.getId(), airportGate.getGate(),
                    airportGate.getAirport().getName());
        } else {
            System.out.println("Airport Gate not found.");
        }
    }

    private Airport selectAirport(Scanner scanner) {
        System.out.println("Choose the airport ID:");
        airportService.getAllAirports().forEach(airport -> {
            System.out.printf("ID: %d, Name: %s \n", airport.getId(), airport.getName());
        });

        Airport chosenAirport = null;
        while (chosenAirport == null) {
            Long airportId = scanner.nextLong();
            scanner.nextLine();

            Optional<Airport> optionalAirport = airportService.getAirportById(airportId);
            if (optionalAirport.isPresent()) {
                chosenAirport = optionalAirport.get();
            } else {
                System.out.println("Invalid Airport ID. Please try again.");
            }
        }
        return chosenAirport;
    }

    private AirportGate selectAirportGate(Scanner scanner) {
        System.out.println("Choose the airport gate you want to modify:");
        airportGateService.getAllAirportGates().forEach(gate -> {
            System.out.printf("ID: %d, Gate: %s, Airport: %s \n", gate.getId(), gate.getGate(),
                    gate.getAirport().getName());
        });

        AirportGate chosenAirportGate = null;
        while (chosenAirportGate == null) {
            Long gateId = scanner.nextLong();
            scanner.nextLine();

            Optional<AirportGate> optionalAirportGate = airportGateService.getAirportGateById(gateId);
            if (optionalAirportGate.isPresent()) {
                chosenAirportGate = optionalAirportGate.get();
            } else {
                System.out.println("Invalid Airport Gate ID. Please try again.");
            }
        }
        return chosenAirportGate;
    }
}
