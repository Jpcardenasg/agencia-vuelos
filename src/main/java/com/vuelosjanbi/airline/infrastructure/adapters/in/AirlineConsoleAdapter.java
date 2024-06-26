package com.vuelosjanbi.airline.infrastructure.adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.Scanner;

import com.vuelosjanbi.airline.application.AirlineService;
import com.vuelosjanbi.airline.domain.models.Airline;

@Controller
public class AirlineConsoleAdapter {

    @Autowired
    private AirlineService airlineService;

    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n");
            System.out.println("1. Create Airline");
            System.out.println("2. Update Airline");
            System.out.println("3. Delete Airline");
            System.out.println("4. Get Airline by Id");
            System.out.println("5. Get Airline by Name");
            System.out.println("6. Get All Airlines");
            System.out.println("0. Exit");

            switch (scanner.nextInt()) {
                case 1:
                    createAirline(scanner);
                    break;
                case 2:
                    updateAirline(scanner);
                    break;
                case 3:
                    deleteAirline(scanner);
                    break;
                case 4:
                    getAirlineById(scanner);
                    break;
                case 5:
                    getAirlineByName(scanner);
                    break;
                case 6:
                    getAllAirlines();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public void createAirline(Scanner scanner) {
        System.out.println("Enter Airline Name");
        String name = scanner.nextLine();
        Airline airline = new Airline(name);
        airlineService.createAirline(airline);
    }

    public void updateAirline(Scanner scanner) {
        System.out.println("Enter Airline Id");
        Long id = scanner.nextLong();
        System.out.println("Enter new Airline Name");
        String name = scanner.nextLine();
        Airline airline = new Airline(id, name);
        airlineService.updateAirline(airline);
    }

    public void deleteAirline(Scanner scanner) {
        System.out.println("Enter Airline Id");
        Long id = scanner.nextLong();
        airlineService.deleteAirlineById(id);
    }

    public void getAirlineById(Scanner scanner) {
        System.out.println("Enter Airline Id");
        Long id = scanner.nextLong();
        System.out.println(airlineService.getAirlineById(id));
    }

    public void getAirlineByName(Scanner scanner) {
        System.out.println("Enter Airline Name");
        String name = scanner.nextLine();
        System.out.println(airlineService.getAirlinesByName(name));
    }

    public void getAllAirlines() {
        System.out.println(airlineService.getAllAirlines());
    }
}
