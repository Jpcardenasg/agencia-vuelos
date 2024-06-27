package com.vuelosjanbi;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vuelosjanbi.airport.infrastructure.adapters.in.AirportConsoleAdapter;
import com.vuelosjanbi.plane.infrastructure.adapters.in.PlaneConsoleAdapter;
import com.vuelosjanbi.planeManufacturer.infrastructure.adapters.in.PlaneManufacturerConsoleAdapter;
import com.vuelosjanbi.planeRevision.application.PlaneRevisionService;
import com.vuelosjanbi.trip.application.TripService;
import com.vuelosjanbi.trip.infrastructure.adapters.in.TripConsoleAdapter;
import com.vuelosjanbi.tripCrew.infrastructure.adapters.in.TripCrewConsoleAdapter;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private PlaneConsoleAdapter planeConsoleAdapter;

    @Autowired
    private TripConsoleAdapter tripConsoleAdapter;

    @Autowired
    private TripCrewConsoleAdapter tripCrewConsoleAdapter;

    @Autowired
    private AirportConsoleAdapter airportConsoleAdapter;

    @Autowired
    private PlaneManufacturerConsoleAdapter planeManufacturerConsoleAdapter;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the repository type:");
        System.out.println("1. JPA");
        System.out.println("2. MySQL Manual Queries");

        int choice = scanner.nextInt();
        boolean useJpa = (choice == 1);

        while (true) {
            System.out.println("Choose the entity to manage:");
            System.out.println("1. Airport");
            System.out.println("2. Plane");
            System.out.println("3. Trip");
            System.out.println("4. Trip Crew");
            System.out.println("5. Create basic data for the application");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    airportConsoleAdapter.start(useJpa);
                    break;
                case 2:
                    planeConsoleAdapter.start(useJpa);
                    break;
                case 3:
                    tripConsoleAdapter.start(useJpa);
                    break;
                case 4:
                    tripCrewConsoleAdapter.start(useJpa);
                    break;
                case 5:
                    createBasicData();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    public void createBasicData() {

    }
}